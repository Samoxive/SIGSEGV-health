package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_list.*


class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val rvUsers = rv_user_list

        val users = UserList.createMockObjects(20)
        val adapter = UserListAdapter(users)
        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(this)
    }
}
