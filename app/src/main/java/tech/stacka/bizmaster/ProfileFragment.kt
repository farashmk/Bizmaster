package tech.stacka.bizmaster


import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    private lateinit var prefs : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = activity!!.getSharedPreferences("com.stackatech.bizmaster",
            AppCompatActivity.MODE_PRIVATE
        )
        tv_profile_name.setText(prefs.getString("name","no name"))
        tvlocation.setText(prefs.getString("place","no place"))
        tvmessage.setText(prefs.getString("email","no mail"))
        tvphone.setText(prefs.getString("phone","no number"))

    }


}
