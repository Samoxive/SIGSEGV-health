package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import kotlinx.android.synthetic.main.activity_user_overview.*
import kotlinx.android.synthetic.main.fragment_user_calories.*
import sigsegv.com.health.api.entities.*
import sigsegv.com.health.api.getUser
import sigsegv.com.health.api.getUserData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserOverviewActivity : AppCompatActivity() {

    private lateinit var mDemoCollectionPagerAdapter: UserInfoPagerAdapter
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        val bundle = intent.extras
        var email: String = "blank"
        if (bundle?.getString("email") != null) {
            email = bundle.getString("email")!!
        }

        val stubUserSettingData = generateStubUserSettingData()

        mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, stubUserSettingData, null)
        mViewPager = findViewById(R.id.pager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        AsyncAction({ getUser(this@UserOverviewActivity, email) }, { r ->
            mDemoCollectionPagerAdapter.changeUserSettings(r.settings.userSettings)
        })
        AsyncAction({ getUserData(this@UserOverviewActivity, email) }, { r ->
            mDemoCollectionPagerAdapter.changeUserData(r)
            mViewPager.findViewWithTag<View>("dalga").findViewById<BarChart>(R.id.calories_bar_chart).invalidate()
        })
        tab_layout.setupWithViewPager(mViewPager)

    }

    fun generateStubUserSettingData(): UserSettings {
        return UserSettings(
            "Hamdi Burak", "Usul",
            "1997-01-02".toDate(), 78, 184, "male",
            "fitness", "23:00:00.000".toViitaTime(), "08:30:00.000".toViitaTime(),
            10000, 1000
        )
    }

    class UserInfoPagerAdapter(
        private val fm: FragmentManager,
        var userSettings: UserSettings,
        var userData: UserData?
    ) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = 4

        fun calcAge(birthDate: Date): Int {
            //todo implement
            return 21
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


        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            if(position == 1)
                container.tag = "dalga"
            return super.instantiateItem(container, position)
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

        fun changeUserSettings(data: UserSettings) {
            this.userSettings = data
            this.notifyDataSetChanged()
        }

        fun changeUserData(data: UserData) {
            this.userData = data
            this.notifyDataSetChanged()
        }

        override fun getPageTitle(position: Int): CharSequence {
            when (position) {
                0 -> return "Overview"
                1 -> return "Calories"
                else -> {
                    return "Other"
                }
            }
        }
    }


}
