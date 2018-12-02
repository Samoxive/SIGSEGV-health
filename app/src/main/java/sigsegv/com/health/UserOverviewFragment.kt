package sigsegv.com.health

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_overview.*

private const val ARG_NAME = "name"
private const val ARG_AGE_GENDER = "age_and_gender"
private const val ARG_HEIGHT = "height"
private const val ARG_WEIGHT = "weight"
private const val ARG_MISSION = "mission"
private const val ARG_DAILY_STEP_GOAL = "daily_step_goal"
private const val ARG_DAILY_CALORIE_GOAL = "daily_calorie_goal"
private const val ARG_SLEEP_GOAL = "sleep_goal"
private const val ARG_WAKE_GOAL = "wake_goal"


class UserOverviewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var name: String
    private lateinit var ageGenderVal: String
    private var height: Int = 0
    private var weight: Int = 0
    private lateinit var mission: String
    private var dailyStepGoal: Int = 0
    private var dailyCalorieGoal: Int = 0
    private lateinit var sleepGoal: String
    private lateinit var wakeGoal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)!!
            ageGenderVal = it.getString(ARG_AGE_GENDER)!!
            height = it.getInt(ARG_HEIGHT)
            weight = it.getInt(ARG_WEIGHT)
            mission = it.getString(ARG_MISSION)!!
            dailyStepGoal = it.getInt(ARG_DAILY_STEP_GOAL)
            dailyCalorieGoal = it.getInt(ARG_DAILY_CALORIE_GOAL)
            sleepGoal = it.getString(ARG_SLEEP_GOAL)!!
            wakeGoal = it.getString(ARG_WAKE_GOAL)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_overview, container, false)
    }

    private fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nameUser.text = name
        ageGender.text = ageGenderVal
        heightText.text = resources.getString(R.string.height_amount_in_cm, height)
        weightText.text = resources.getString(R.string.weight_amount_in_kg, weight)
        missionText.text = mission
        dailyCaloriesVal.text = dailyCalorieGoal.toString()
        dailyStepsVal.text = dailyStepGoal.toString()
        sleepTimeVal.text = sleepGoal
        wakeTimeVal.text = wakeGoal
        var heightInMeters = height.toDouble()
        while(heightInMeters >= 10) heightInMeters /= 10
        val bmi = weight.toDouble() / (heightInMeters * heightInMeters)
        bmiValue.text = bmi.format(2)

        if(bmi <= 18.5)
            bmiMeaning.text = context!!.getText(R.string.bmi_underweight)
        else if(bmi <= 24.9)
            bmiMeaning.text = context!!.getText(R.string.bmi_normal_weight)
        else if(bmi <= 29.9)
            bmiMeaning.text = context!!.getText(R.string.bmi_overweight)
        else
            bmiMeaning.text = context!!.getText(R.string.bmi_obesity)

        super.onViewCreated(view, savedInstanceState)
    }

}
