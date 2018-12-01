package sigsegv.com.health

import android.graphics.Bitmap
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.view.LayoutInflater



class UserListAdapter(val mUsers:List<UserList>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_user_selector, p0, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = mUsers.get(p1)

        val textView = p0.nameTextView
        textView.text = user.userName

        val imageView = p0.userImageView
        imageView.setImageBitmap(user.userImage)

        val button = p0.messageButton
        button.text = "TEST"
        button.isEnabled = true
    }

    inner class ViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var userImageView: ImageView
        var nameTextView: TextView
        var messageButton: Button

        init {
            userImageView =  itemView.findViewById(R.id.user_image)
            nameTextView = itemView.findViewById(R.id.user_name)
            messageButton = itemView.findViewById(R.id.message_button) as Button
        }
    }

}


data class UserList (
    val userName: String,
    val userImage: Bitmap?
) {
    companion object {
        fun createMockObjects(n: Int) : List<UserList>{
            val contacts = ArrayList<UserList>()

            for (i in 1..n) {
                contacts.add(UserList("Person", null))
            }

            return contacts
        }
    }
}