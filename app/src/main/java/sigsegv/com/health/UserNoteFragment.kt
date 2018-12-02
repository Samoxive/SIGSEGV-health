package sigsegv.com.health

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_user_note.*
import kotlinx.android.synthetic.main.fragment_user_overview.*
import sigsegv.com.health.api.getUserNote
import sigsegv.com.health.api.updateUserNote

private const val ARG_NOTE = "note"
private const val ARG_EMAIL = "email"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserOverviewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UserOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

class UserNoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getUserNote(this.context!!, email)
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

    companion object {
        @JvmStatic
        fun newInstance(name: String) =
            UserOverviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NOTE, name)
                }
            }
    }
}
