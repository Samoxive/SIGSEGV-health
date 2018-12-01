package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import sigsegv.com.health.api.entities.ViitaUserSettings
import java.lang.Exception

class UserOverviewActivity : AppCompatActivity() {


    private lateinit var mDemoCollectionPagerAdapter: UserInfoPagerAdapter
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        val bundle = intent.extras
        val email: String
        if (bundle?.getString("email") != null){
            email = bundle.getString("email")!!
            val data = stubFunc(email)
            mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, data)
            mViewPager = findViewById(R.id.pager)
            mViewPager.adapter = mDemoCollectionPagerAdapter
        }
    }

    fun stubFunc(email: String): ViitaUserSettings {
        //TODO will be implemented
        return ViitaUserSettings(
            "Hamdi Burak", "Usul",
            "07.11.1997", 78, 184, "male",
            "fitness", "11:30 pm", "08:30 am",
            10000, 5000
        )
    }


    class UserInfoPagerAdapter(fm: FragmentManager, data: ViitaUserSettings) : FragmentPagerAdapter(fm) {

        private var userSettingData: ViitaUserSettings? = null


        fun UserInfoPagerAdapter(fm: FragmentManager, data: ViitaUserSettings) {
            userSettingData = data
        }


        override fun getCount(): Int = 4

        fun calcAge(birthDate: String): Int {
            //TODO Implement this
            return 21
        }

        override fun getItem(i: Int): Fragment {
            val fragment = UserOverviewFragment()
            fragment.arguments = Bundle().apply {
                putString("name", userSettingData?.firstName + " " + userSettingData?.lastName)
                val age = calcAge(userSettingData?.dateOfBirth!!)
                putString("age_and_gender", age.toString() + " " + userSettingData?.gender)
                putInt("height", userSettingData?.height!!)
                putInt("weight", userSettingData?.weight!!)
                putString("mission", userSettingData?.userMission!!)
                putInt("daily_step_goal", userSettingData?.stepsGoal!!)
                putInt("daily_calorie_goal", userSettingData?.caloriesGoal!!)
                putString("sleep_goal", userSettingData?.sleepGoalStart)
                putString("wake_goal", userSettingData?.sleepGoalEnd)
            }
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence {
            return if (position == 0) {
                "Overview"
            } else {
                "Other"
            }
        }
    }


}
