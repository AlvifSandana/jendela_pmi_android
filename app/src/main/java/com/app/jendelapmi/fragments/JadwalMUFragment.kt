package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.jendelapmi.R
import com.app.jendelapmi.adapter.RVJadwalMobileUnitAdapter
import com.app.jendelapmi.models.MobileUnitModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import kotlinx.android.synthetic.main.fragment_jadwal_m_u.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class JadwalMUFragment : Fragment() {

    lateinit var button_back: ImageView
    lateinit var rvTable: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_m_u, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define view component
        button_back = back_btn
        rvTable = row_container_data
        // button listener
        button_back.setOnClickListener {
            activity?.onBackPressed()
        }
        getDataJadwalMobilUnit()
    }

    private fun getDataJadwalMobilUnit() {
        try {
            // instance of shared preference helper
            val prefs = customPreference(requireContext(), "userdata")
            // get jadwal data
            ApiService.endpoint.getMobileUnit(prefs.api_token.toString(), "pendonor")
                .enqueue(object : Callback<MobileUnitModel> {
                    override fun onResponse(
                        call: Call<MobileUnitModel>,
                        response: Response<MobileUnitModel>
                    ) {
                        val status = response.body()?.status
                        val message = response.body()?.message
                        if (status == "success") {
                            if (response.body()?.data != null) {
                                // show success toast message
                                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                                // set recyclerview adapter
                                rvTable.adapter = RVJadwalMobileUnitAdapter(response.body()?.data!!)
                                // set recyclerview layout
                                rvTable.layoutManager = LinearLayoutManager(requireContext())
                            }
                        }
                    }

                    override fun onFailure(call: Call<MobileUnitModel>, t: Throwable) {
                        Log.d("debug_jadwal_MU", t.toString())
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                    }

                })
        } catch (e: IOException) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): JadwalMUFragment = JadwalMUFragment()
    }
}