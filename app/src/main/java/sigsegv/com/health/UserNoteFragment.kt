package sigsegv.com.health

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import sigsegv.com.health.api.getUserNote
import sigsegv.com.health.api.updateUserNote


class UserNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_note, container, false)
    }

    override fun onResume() {
        super.onResume()
        val act = activity!! as UserOverviewActivity
        val email = act.email
        val editText = act.findViewById<EditText>(R.id.noteText)
        editText.setText(getUserNote(context!!, email))
        val resetButton = act.findViewById<Button>(R.id.resetBtn)
        val setButton = act.findViewById<Button>(R.id.setBtn)
        resetButton.setOnClickListener {
            editText.setText("")
            updateUserNote(context!!, email, "")
        }

        setButton.setOnClickListener {
            val text = editText.text.toString()
            updateUserNote(context!!, email, text)
        }

    }

}
