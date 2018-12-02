package sigsegv.com.health

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_note.*
import kotlinx.android.synthetic.main.fragment_user_overview.*
import sigsegv.com.health.api.getUserNote

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
    // TODO: Rename and change types of parameters
    private lateinit var note: Editable
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getCharSequence(ARG_NOTE)!! as Editable
            email = it.getString(ARG_EMAIL)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        noteText.text = note
        super.onViewCreated(view, savedInstanceState)
        //getUserNote(this.context!!, email)
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
