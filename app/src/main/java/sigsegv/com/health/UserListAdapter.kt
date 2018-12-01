package sigsegv.com.health

import android.graphics.Bitmap
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.view.LayoutInflater



class UserListAdapter(private val mUsers: List<UserList>): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    val itemOnClick: (View, Int, Int) -> Unit = { view, position, type ->
        Log.d("asd", "test")
    }

    fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, itemViewType)
        }
        return this
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_user_selector, p0, false)
        val vH = ViewHolder(contactView)
        vH.onClick(itemOnClick)
        return vH
    }

    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = mUsers[p1]

        val textView = p0.nameTextView
        textView.text = user.userName

        val imageView = p0.userImageView
        imageView.setImageBitmap(user.userImage)
    }

    inner class ViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userImageView: ImageView = itemView.findViewById(R.id.user_image)
        var nameTextView: TextView = itemView.findViewById(R.id.user_name)

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