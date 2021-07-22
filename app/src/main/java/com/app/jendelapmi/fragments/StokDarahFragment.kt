package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.jendelapmi.R
import com.app.jendelapmi.models.StokDarahModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import kotlinx.android.synthetic.main.fragment_stok_darah.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StokDarahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stok_darah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStokDarah()
    }

    private fun getStokDarah() {
        try {
            val prefs = customPreference(requireContext(), "userdata")
            ApiService.endpoint.getStokDarah(prefs.api_token.toString(), "pendonor")
                .enqueue(object: Callback<StokDarahModel> {
                    override fun onResponse(
                        call: Call<StokDarahModel>,
                        response: Response<StokDarahModel>
                    ) {
                        if (response.body()?.status == "success") {
                            Log.d("stok", response.body()?.data.toString())
                            val data = response.body()?.data
                            // WB
                            wb_a.text = data?.get(0)?.stok?.A.toString()
                            wb_b.text = data?.get(0)?.stok?.B.toString()
                            wb_ab.text = data?.get(0)?.stok?.AB.toString()
                            wb_o.text = data?.get(0)?.stok?.O.toString()
                            wb_total.text = data?.get(0)?.stok?.Total.toString()
                            // PRC
                            prc_a.text = data?.get(1)?.stok?.A.toString()
                            prc_b.text = data?.get(1)?.stok?.B.toString()
                            prc_ab.text = data?.get(1)?.stok?.AB.toString()
                            prc_o.text = data?.get(1)?.stok?.O.toString()
                            prc_total.text = data?.get(1)?.stok?.Total.toString()
                            // WB
                            tc_a.text = data?.get(2)?.stok?.A.toString()
                            tc_b.text = data?.get(2)?.stok?.B.toString()
                            tc_ab.text = data?.get(2)?.stok?.AB.toString()
                            tc_o.text = data?.get(2)?.stok?.O.toString()
                            tc_total.text = data?.get(2)?.stok?.Total.toString()
                            // WB
                            ffp_a.text = data?.get(3)?.stok?.A.toString()
                            ffp_b.text = data?.get(3)?.stok?.B.toString()
                            ffp_ab.text = data?.get(3)?.stok?.AB.toString()
                            ffp_o.text = data?.get(3)?.stok?.O.toString()
                            ffp_total.text = data?.get(3)?.stok?.Total.toString()

                        }
                    }

                    override fun onFailure(call: Call<StokDarahModel>, t: Throwable) {
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                    }

                })
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): StokDarahFragment = StokDarahFragment()
    }
}