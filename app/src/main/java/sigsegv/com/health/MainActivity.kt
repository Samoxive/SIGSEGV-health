package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }
}
