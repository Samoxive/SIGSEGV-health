package sigsegv.com.health

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater

import sigsegv.com.health.api.entities.SignInUserData

interface OnItemClickListener{
    fun onClick(users: List<SignInUserData>, itemPosition: Int)
}

class UserListAdapter(private var mUsers: List<SignInUserData>, private val clickListener: OnItemClickListener): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val context = p0.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.item_user_selector, p0, false)
        val vH = ViewHolder(itemView)
        itemView.setOnClickListener {
            clickListener.onClick(mUsers, vH.adapterPosition)
        }

        return vH
    }

    fun setData(mUsers: List<SignInUserData>){
        this.mUsers = mUsers
        this.notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return mUsers.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val user = mUsers[p1]

        val textView = p0.nameTextView
        val str = user.settings.userSettings.firstName +" "+ user.settings.userSettings.lastName
        textView.text = str
        //val imageView = p0.userImageView
        //imageView.setImageBitmap(null)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //var userImageView: ImageView = itemView.findViewById(R.id.user_image)
        var nameTextView: TextView = itemView.findViewById(R.id.user_name)

    }

}