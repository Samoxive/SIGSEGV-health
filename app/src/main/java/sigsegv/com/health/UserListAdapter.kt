package sigsegv.com.health

import android.graphics.Bitmap
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent

interface OnItemClickListener{
    fun onClick(itemPosition: Int)
}

class UserListAdapter(private val mUsers: List<UserList>, private val clickListener: OnItemClickListener): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.item_user_selector, p0, false)
        itemView.setOnClickListener {
            clickListener.onClick(p1)
        }
        val vH = ViewHolder(itemView)

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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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