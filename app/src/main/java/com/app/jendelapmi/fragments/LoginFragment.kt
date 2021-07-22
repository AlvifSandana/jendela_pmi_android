package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.jendelapmi.R
import com.app.jendelapmi.models.LoginResponseModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.TTL
import com.app.slider.PreferenceHelper.address
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import com.app.slider.PreferenceHelper.fullname
import com.app.slider.PreferenceHelper.golongan_darah
import com.app.slider.PreferenceHelper.id
import com.app.slider.PreferenceHelper.password
import com.app.slider.PreferenceHelper.status
import com.app.slider.PreferenceHelper.userEmail
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define all view from xml
        val email = email.editText?.text
        val password = password.editText?.text
        val btn_login = btn_l_login
        val btn_register = btn_l_daftar

        // listener for btn_login
        btn_login.setOnClickListener {
            login(email.toString(), password.toString() )
        }

        // listener for btn_register
        btn_register.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.rootFragment, RegisterFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
    }

    companion object {
        // in companion object,
        // all method can be called without
        // creating an instance from specified class
        fun getInstance(): LoginFragment = LoginFragment()
    }

    // login method
    private fun login(email: String, password: String){
        try {
            // define shared preference object
            val prefs = customPreference(requireActivity(), "userdata")
            // validate email and password
            if (email != "" && password != "") {
                // call api service and post login
                ApiService.endpoint.doLogin(email, password)
                    .enqueue(object : Callback<LoginResponseModel>{
                        // when process success
                        override fun onResponse(
                            call: Call<LoginResponseModel>,
                            response: Response<LoginResponseModel>
                        ) {
                            Log.d("RESPONSE", response.toString())
                            val resdata = response.body()?.data
                            if (response.body()?.status == "success"){
                                prefs.id = resdata?.get(0)?.id!!.toInt()
                                prefs.fullname = resdata[0].nama_pendonor
                                prefs.userEmail = resdata[0].email
                                prefs.password = resdata[0].password
                                prefs.address = resdata[0].alamat
                                prefs.api_token = resdata[0].api_token
                                prefs.status = resdata[0].status
                                prefs.TTL = resdata[0].ttl
                                prefs.golongan_darah = resdata[0].golongan_darah
                                Toast.makeText(activity, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(activity, response.body()?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                        // when process failure
                        override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                            Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
                        }
                    })
            } else {
                Toast.makeText(activity, "Field tidak boleh kosong!", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
        }

    }
}