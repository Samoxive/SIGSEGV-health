package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_user_overview.*
import kotlinx.android.synthetic.main.activity_user_overview.view.*
import sigsegv.com.health.api.entities.ViitaUserSettings
import android.support.design.widget.TabLayout
import sigsegv.com.health.api.entities.UserSettings
import sigsegv.com.health.api.entities.toDate
import sigsegv.com.health.api.entities.toViitaTime
import sigsegv.com.health.api.getUser
import java.util.*

class UserOverviewActivity : AppCompatActivity() {

    private lateinit var mDemoCollectionPagerAdapter: UserInfoPagerAdapter
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        val bundle = intent.extras
        var email: String = "blank"
        if (bundle?.getString("email") != null){
            email = bundle.getString("email")!!
        }
        val data = stubFunc()
        mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, data)
        mViewPager = findViewById(R.id.pager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        AsyncAction({ getUser(this@UserOverviewActivity, email)}, {r -> mDemoCollectionPagerAdapter.changeData(r.settings.userSettings) })



        tab_layout.setupWithViewPager(mViewPager)



    }

    fun stubFunc(): UserSettings {
        return UserSettings("Hamdi Burak", "Usul",
            "07.11.1997".toDate(), 78, 184, "male",
            "fitness", "23:00:00.000".toViitaTime(), "08:30:00.000".toViitaTime(),
            10000, 1000)

        /*return ViitaUserSettings(
            "Hamdi Burak", "Usul",
            "07.11.1997", 78, 184, "male",
            "fitness", "11:30 pm", "08:30 am",
            10000, 1000
        )*/
    }


    class UserInfoPagerAdapter(private val fm: FragmentManager, var data: UserSettings) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = 4

        fun calcAge(birthDate: Date): Int {
            //todo implement
            return 21
        }

        override fun getItem(i: Int): Fragment {
            val fragment = UserOverviewFragment()
            fragment.arguments = Bundle().apply {
                putString("name", data.firstName + " " + data.lastName)
                val age = calcAge(data.dateOfBirth)
                putString("age_and_gender", age.toString() + " " + data.gender)
                putInt("height", data.height)
                putInt("weight", data.weight)
                putString("mission", data.userMission)
                putInt("daily_step_goal", data.stepsGoal)
                putInt("daily_calorie_goal", data.caloriesGoal)
                putString("sleep_goal", data.sleepGoalStart.toString())
                putString("wake_goal", data.sleepGoalEnd.toString())
            }
            return fragment
        }

        fun changeData(data : UserSettings){
            this.data = data
            this.notifyDataSetChanged()
        }

        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> return "Overview"
                1 -> return "Calories"
                else -> {
                    return "Other"
                }
            }
        }
    }


}
