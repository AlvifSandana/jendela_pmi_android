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
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    // define components from layout
    lateinit var txt_nama: com.google.android.material.textfield.TextInputLayout
    lateinit var txt_alamat: com.google.android.material.textfield.TextInputLayout
    lateinit var txt_ttl: com.google.android.material.textfield.TextInputLayout
    lateinit var txt_goldarah: com.google.android.material.textfield.TextInputLayout
    lateinit var txt_email: com.google.android.material.textfield.TextInputLayout
    lateinit var txt_password: com.google.android.material.textfield.TextInputLayout
    lateinit var button_register: com.google.android.material.button.MaterialButton
    lateinit var button_login: com.google.android.material.button.MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // define fields
        txt_nama = txt_reg_nama
        txt_alamat = txt_reg_alamat
        txt_goldarah = txt_reg_goldarah
        txt_ttl = txt_reg_ttl
        txt_email = txt_reg_email
        txt_password = txt_reg_password

        // define button
        button_register = btn_register
        button_login = btn_login

        // set listener for buttons
        button_register.setOnClickListener {
            registerPendonor(
                txt_nama.editText?.text.toString(),
                txt_email.editText?.text.toString(),
                txt_password.editText?.text.toString(),
                txt_alamat.editText?.text.toString(),
                txt_ttl.editText?.text.toString(),
                txt_goldarah.editText?.text.toString(),
            )
        }
        button_login.setOnClickListener {
            gotoLoginFragment()
        }
    }

    /**
     * handle changing fragment
     */
    private fun gotoLoginFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.rootFragment, LoginFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    /**
     * handle register process
     */
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
                    if (status == "success") {
                        Toast.makeText(activity, "Berhasil registrasi! Silahkan klik tombol login.", Toast.LENGTH_LONG).show()
                        gotoLoginFragment()
                    } else {
                        Toast.makeText(activity, "Email telah terdaftar pada akun lain.", Toast.LENGTH_SHORT).show()
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