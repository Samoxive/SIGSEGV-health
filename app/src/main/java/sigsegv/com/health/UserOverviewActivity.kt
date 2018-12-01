package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_overview.*
import sigsegv.com.health.api.entities.ViitaUserSettings
import java.lang.Exception

class UserOverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_overview)
        val bundle = intent.extras
        val email : String
        if(bundle?.getString("email") != null)
            email = bundle.getString("email")!!
        else
            throw Exception("Bundle is null")

        val data = stubFunc(email)
        nameUser.text = data.firstName + " " + data.lastName
        val age = calcAge(data.dateOfBirth)
        ageGender.text = age.toString() + " / " + data.gender
        heightText.text = data.height.toString() + " m"
        weightText.text = data.weight.toString() + " kg"
        missionText.text = data.userMission
    }



    fun calcAge(birthDate : String) : Int{
        //TODO Implement this
        return 21
    }

    fun stubFunc(email : String) : ViitaUserSettings{
        //TODO will be implemented
        return ViitaUserSettings("Hamdi Burak","Usul",
            "07.11.1997",78,184,"male",
            "fitness", "some val", "some val",
            10000, 5000)
    }

}
