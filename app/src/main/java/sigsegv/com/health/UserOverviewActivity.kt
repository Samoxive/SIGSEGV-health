package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_user_overview.*
import org.joda.time.DateTime
import org.joda.time.Years
import sigsegv.com.health.api.entities.*
import sigsegv.com.health.api.getUser
import sigsegv.com.health.api.getUserData
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.collections.ArrayList

class UserOverviewActivity : AppCompatActivity() {

    private lateinit var mDemoCollectionPagerAdapter: UserInfoPagerAdapter
    private lateinit var mViewPager: ViewPager
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        val bundle = intent.extras
        email = "blank"
        if (bundle?.getString("email") != null) {
            email = bundle.getString("email")!!
        }

        val lock = ReentrantLock()
        var filled = 0

        var userData: UserData? = null
        var userSettings: UserSettings? = null

        AsyncAction({ getUser(this@UserOverviewActivity, email) }, { r ->
            run {
                userSettings = r.settings.userSettings
                lock.lock()
                filled++
                if (filled == 2) {
                    mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, userSettings!!, userData)
                    mViewPager = findViewById(R.id.pager)
                    mViewPager.adapter = mDemoCollectionPagerAdapter
                    tab_layout.setupWithViewPager(mViewPager)
                }
                lock.unlock()

            }
        })
        AsyncAction({ getUserData(this@UserOverviewActivity, email) }, { r ->
            run {
                userData = r
                lock.lock()
                filled++
                if (filled == 2) {
                    mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, userSettings!!, userData)
                    mViewPager = findViewById(R.id.pager)
                    mViewPager.adapter = mDemoCollectionPagerAdapter
                    tab_layout.setupWithViewPager(mViewPager)
                }
                lock.unlock()
            }
        })

    }

    class UserInfoPagerAdapter(
        private val fm: FragmentManager,
        var userSettings: UserSettings,
        var userData: UserData?
    ) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = 4

        private var last: UserCaloriesFragment? = null

        fun calcAge(birthDate: Date): Int {
            val now  = DateTime(Calendar.getInstance().time)
            val then = DateTime(birthDate)
            return Years.yearsBetween(then, now).years
        }


        fun getDateList(v: List<HourlyDataDto>): ArrayList<String> {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val retVal: ArrayList<String> = ArrayList()
            for (item in v) {
                retVal.add(format.format(item.date))
            }
            return retVal
        }

        fun getSingleStr(dt: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd")
            return format.format(dt)
        }


        override fun getItem(i: Int): Fragment {
            if (i == 1) {
                val fragment = UserCaloriesFragment()
                val local = userData
                if (local != null) {
                    val hourlyDataDtoList: List<HourlyDataDto> = local.calories
                    fragment.arguments = Bundle().apply {
                        putStringArrayList("dates", getDateList(hourlyDataDtoList))
                        for (item in hourlyDataDtoList) {
                            putIntegerArrayList(getSingleStr(item.date), item.values as java.util.ArrayList<Int>)
                        }
                    }
                }
                last = fragment
                return fragment
            } else {
                val fragment = UserOverviewFragment()
                fragment.arguments = Bundle().apply {
                    putString("name", userSettings.firstName + " " + userSettings.lastName)
                    val age = calcAge(userSettings.dateOfBirth)
                    putString("age_and_gender", age.toString() + " " + userSettings.gender)
                    putInt("height", userSettings.height)
                    putInt("weight", userSettings.weight)
                    putString("mission", userSettings.userMission)
                    putInt("daily_step_goal", userSettings.stepsGoal)
                    putInt("daily_calorie_goal", userSettings.caloriesGoal)
                    putString("sleep_goal", userSettings.sleepGoalStart.toText())
                    putString("wake_goal", userSettings.sleepGoalEnd.toText())
                }
                return fragment
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Overview"
                1 -> "Calories"
                else -> {
                    "Other"
                }
            }
        }
    }


}
