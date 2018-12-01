package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import sigsegv.com.health.api.entities.ViitaUserSettings

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
        }
        //TODO TIE IT
        val data = stubFunc("some val")
        mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, data)
        mViewPager = findViewById(R.id.pager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

    }

    fun stubFunc(email: String): ViitaUserSettings {
        return ViitaUserSettings(
            "Hamdi Burak", "Usul",
            "07.11.1997", 78, 184, "male",
            "fitness", "11:30 pm", "08:30 am",
            10000, 1000
        )
    }


    class UserInfoPagerAdapter(private val fm: FragmentManager, val data: ViitaUserSettings) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = 4

        fun calcAge(birthDate: String): Int {
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
                putString("sleep_goal", data.sleepGoalStart)
                putString("wake_goal", data.sleepGoalEnd)
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
