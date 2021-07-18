package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.jendelapmi.R
import com.app.jendelapmi.models.UpdateProfileResponseModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.TTL
import com.app.slider.PreferenceHelper.address
import com.app.slider.PreferenceHelper.customPreference
import com.app.slider.PreferenceHelper.fullname
import com.app.slider.PreferenceHelper.golongan_darah
import com.app.slider.PreferenceHelper.id
import com.app.slider.PreferenceHelper.password
import com.app.slider.PreferenceHelper.status
import com.app.slider.PreferenceHelper.userEmail
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileFragment : Fragment() {

    private val fullname = txt_edit_fullname.editText
    private val email = txt_edit_email.editText
    private val password = txt_edit_password.editText
    private val alamat = txt_edit_alamat.editText
    private val ttl = txt_edit_ttl.editText
    private val golongan_darah = txt_edit_golongan_darah.editText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define field from xml

        btn_edit_profil.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        try {
            val prefs = customPreference(requireContext(), "userdata")
            ApiService.endpoint.updateProfile(
                prefs.id, fullname?.text.toString(), email?.text.toString(),
                password?.text.toString(), alamat?.text.toString(), ttl?.text.toString(),
                golongan_darah?.text.toString()
            ).enqueue(object: Callback<UpdateProfileResponseModel> {
                override fun onResponse(
                    call: Call<UpdateProfileResponseModel>,
                    response: Response<UpdateProfileResponseModel>
                ) {
                    val status = response.body()?.status
                    val message = response.body()?.message
                    val data = response.body()?.data

                    if (status == "success"){
                        prefs.fullname = data!![0].nama_pendonor
                        prefs.userEmail = data[0].email
                        prefs.password = data[0].password
                        prefs.address = data[0].alamat
                        prefs.TTL = data[0].ttl
                        prefs.golongan_darah = data[0].golongan_darah
                        prefs.status = data[0].status
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UpdateProfileResponseModel>, t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                }

            })
        } catch (e: Exception) {
            Log.d("RESPONSE_ERROR", e.toString())
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): EditProfileFragment = EditProfileFragment()
    }
}