package sigsegv.com.health

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_overview.*
import java.lang.Exception

private const val ARG_NAME = "name"
private const val ARG_AGE_GENDER = "age_and_gender"
private const val ARG_HEIGHT = "height"
private const val ARG_WEIGHT = "weight"
private const val ARG_MISSION = "mission"
private const val ARG_DAILY_STEP_GOAL = "daily_step_goal"
private const val ARG_DAILY_CALORIE_GOAL = "daily_calorie_goal"
private const val ARG_SLEEP_GOAL = "sleep_goal"
private const val ARG_WAKE_GOAL = "wake_goal"



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserOverviewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UserOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UserOverviewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var ageGenderVal: String? = null
    private var height: Int = 0
    private var weight: Int = 0
    private var mission: String? = null
    private var dailyStepGoal: Int = 0
    private var dailyCalorieGoal: Int = 0
    private var sleepGoal: String? = null
    private var wakeGoal: String? = null



    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            ageGenderVal = it.getString(ARG_AGE_GENDER)
            height = it.getInt(ARG_HEIGHT)
            weight = it.getInt(ARG_WEIGHT)
            mission = it.getString(ARG_MISSION)
            dailyStepGoal = it.getInt(ARG_DAILY_STEP_GOAL)
            dailyCalorieGoal = it.getInt(ARG_DAILY_CALORIE_GOAL)
            sleepGoal = it.getString(ARG_SLEEP_GOAL)
            wakeGoal = it.getString(ARG_WAKE_GOAL)
        }

        nameUser.text = name
        ageGender.text = ageGenderVal
        heightText.text = height.toString() + " m"
        weightText.text = weight.toString() + " kg"
        missionText.text = mission
        dailyCaloriesVal.text = dailyCalorieGoal.toString()
        dailyStepsVal.text = dailyStepGoal.toString()
        sleepTimeVal.text = sleepGoal
        wakeTimeVal.text = wakeGoal
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_overview, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(name: String, ageGender: String,
                      height: Int, weight: Int, mission : String,
                        dailyStepGoal : Int, dailyCalorieGoal : Int,
                        dailySleepGoal : String, dailyWakeGoal : String) =
            UserOverviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_AGE_GENDER, ageGender)
                    putInt(ARG_HEIGHT, height)
                    putInt(ARG_WEIGHT, weight)
                    putString(ARG_MISSION, mission)
                    putInt(ARG_DAILY_STEP_GOAL, dailyStepGoal)
                    putInt(ARG_DAILY_CALORIE_GOAL, dailyCalorieGoal)
                    putString(ARG_SLEEP_GOAL, dailySleepGoal)
                    putString(ARG_WAKE_GOAL, dailyWakeGoal)
                }
            }
    }
}
