package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import kotlinx.android.synthetic.main.activity_user_overview.*
import sigsegv.com.health.api.entities.UserData
import sigsegv.com.health.api.entities.UserSettings
import sigsegv.com.health.api.entities.toDate
import sigsegv.com.health.api.entities.toViitaTime
import sigsegv.com.health.api.getUser
import sigsegv.com.health.api.getUserData
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

        val stubUserSettingData = generateStubUserSettingData()

        mDemoCollectionPagerAdapter = UserInfoPagerAdapter(supportFragmentManager, stubUserSettingData)
        mViewPager = findViewById(R.id.pager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        AsyncAction({ getUser(this@UserOverviewActivity, email)}, {r -> mDemoCollectionPagerAdapter.changeUserSettings(r.settings.userSettings) })
        AsyncAction({ getUserData(this@UserOverviewActivity, email)}, {r ->  debugFunc(r) })

        tab_layout.setupWithViewPager(mViewPager)
    }

    fun debugFunc(data : UserData){
        
    }

    fun generateStubUserSettingData(): UserSettings {
        return UserSettings("Hamdi Burak", "Usul",
            "07.11.1997".toDate(), 78, 184, "male",
            "fitness", "23:00:00.000".toViitaTime(), "08:30:00.000".toViitaTime(),
            10000, 1000)
    }



    class UserInfoPagerAdapter(private val fm: FragmentManager, var userSettings: UserSettings) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = 4

        fun calcAge(birthDate: Date): Int {
            //todo implement
            return 21
        }

        override fun getItem(i: Int): Fragment {
            if(i == 1){
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
                    putString("sleep_goal", userSettings.sleepGoalStart.toString())
                    putString("wake_goal", userSettings.sleepGoalEnd.toString())
                }
                return fragment
            }else{
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
                    putString("sleep_goal", userSettings.sleepGoalStart.toString())
                    putString("wake_goal", userSettings.sleepGoalEnd.toString())
                }
                return fragment
            }
        }

        fun changeUserSettings(data : UserSettings){
            this.userSettings = data
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
