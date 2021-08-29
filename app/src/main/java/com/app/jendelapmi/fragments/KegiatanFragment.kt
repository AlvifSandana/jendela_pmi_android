package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.jendelapmi.R
import com.app.jendelapmi.adapter.RVKegiatanPMI
import com.app.jendelapmi.models.HomeModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper
import com.app.slider.PreferenceHelper.api_token
import kotlinx.android.synthetic.main.fragment_kegiatan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KegiatanFragment : Fragment() {
    lateinit var rvKegiatan: RecyclerView
    lateinit var buttonBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kegiatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define view components
        buttonBack = back_btn
        rvKegiatan = rv_kegiatan
        getKegiatan()
        // back button listener
        buttonBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    /**
     * get data kegiatan from api
     */
    private fun getKegiatan() {
        try {
            // instance of shared preference helper
            val prefs = PreferenceHelper.customPreference(requireContext(), "userdata")
            // get data kegiatan
            ApiService.endpoint.getKegiatan(prefs.api_token.toString(), "pendonor")
                .enqueue(object : Callback<HomeModel> {
                    override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                        val status = response.body()?.status
                        val message = response.body()?.message
                        val data = response.body()?.data
                        if (status == "success") {
                            if (data != null) {
                                // set rv adapter and layout manager
                                rvKegiatan.adapter = RVKegiatanPMI(data)
                                rvKegiatan.layoutManager = LinearLayoutManager(requireContext())
                            }
                        }
                    }

                    override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                        Log.d("getKegiatanPMI", t.toString())
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                    }

                })
        } catch (e: Exception) {
            Log.d("getKegiatanPMI", e.toString())
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): KegiatanFragment = KegiatanFragment()
    }
}