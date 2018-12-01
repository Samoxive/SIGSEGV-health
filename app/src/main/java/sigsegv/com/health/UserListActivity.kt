package sigsegv.com.health

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView



class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val rvContacts = findViewById(R.id.rvContacts) as RecyclerView

        contacts = UserList.createContactsList(20)
        val adapter = UserListAdapter(contacts)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
    }
}
