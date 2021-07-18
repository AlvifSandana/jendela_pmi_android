package com.app.jendelapmi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.app.jendelapmi.R
import com.app.slider.PreferenceHelper.TTL
import com.app.slider.PreferenceHelper.address
import com.app.slider.PreferenceHelper.customPreference
import com.app.slider.PreferenceHelper.fullname
import com.app.slider.PreferenceHelper.status
import com.app.slider.PreferenceHelper.userEmail
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // create shared preference helper instance
        val prefs = customPreference(requireContext(), "userdata")

        // define field from xml
        val lbl_name = user_name
        val lbl_name_1 = profile_nama
        val lbl_email = profile_email
        val lbl_ttl = profile_ttl
        val lbl_alamat = profile_alamat
        val lbl_status_donor = profile_status
        val btn_edit_profile = btn_edit_profil

        // change text with shared preference value
        lbl_name.text = prefs.fullname
        lbl_email.text = prefs.userEmail
        lbl_ttl.text = prefs.TTL
        lbl_alamat.text = prefs.address
        lbl_status_donor.text = prefs.status
        lbl_name_1.text = prefs.fullname

        // set onClickListener for btn_edit_profile
        btn_edit_profile.setOnClickListener {
            gotoFragment(EditProfileFragment.getInstance())
        }
    }

    private fun gotoFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.rootFragment, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    companion object {
        fun getInstance(): ProfileFragment = ProfileFragment()
    }
}