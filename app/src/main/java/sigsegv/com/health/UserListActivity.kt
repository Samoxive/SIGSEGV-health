package sigsegv.com.health

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_list.*
import sigsegv.com.health.api.getAllUsers


class UserListActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val rvUsers = rv_user_list
        val users = UserList.createMockObjects(20)
        val adapter = UserListAdapter(users, this)

        AsyncAction({ getAllUsers(this@UserListActivity) }, { Log.wtf("Hello", Gson().toJson(it)) })

        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(itemPosition: Int) {
        val intent = Intent(this, UserOverviewActivity::class.java)
        intent.putExtra("email", "a@a.com")
        startActivity(intent)
    }
}
