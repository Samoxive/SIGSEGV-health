package sigsegv.com.health

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_list.*
import sigsegv.com.health.api.entities.SignInUserData
import sigsegv.com.health.api.getAllUsers


class UserListActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val rvUsers = rv_user_list

        val adapter = UserListAdapter(listOf(), this)
        AsyncAction({ getAllUsers(this@UserListActivity) }, {users -> adapter.setData(users) })


        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(users: List<SignInUserData>, itemPosition: Int) {
        val intent = Intent(this, UserOverviewActivity::class.java)
        intent.putExtra("email", users[itemPosition].user.email)
        startActivity(intent)
    }
}
