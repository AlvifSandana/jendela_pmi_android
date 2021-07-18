package com.app.jendelapmi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.jendelapmi.R
import com.app.jendelapmi.models.RegisterResponseModel
import com.app.jendelapmi.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private fun registerPendonor(
        nama: String, email: String, password: String,
        alamat: String, ttl: String, golongan_darah: String
    ){
        try {
            ApiService.endpoint.doRegister(
                nama, email, password, alamat, ttl, golongan_darah
            ).enqueue(object : Callback<RegisterResponseModel> {
                override fun onResponse(
                    call: Call<RegisterResponseModel>,
                    response: Response<RegisterResponseModel>
                ) {
                    val status = response.body()?.status
                    val message = response.body()?.message
                    if (status == "error" || status == "failed") {
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "Berhasil registrasi! Silahkan klik tombol login.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                    Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        } catch (e: Exception) {
            Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
       fun getInstance(): RegisterFragment = RegisterFragment()
    }
}