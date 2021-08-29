package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.jendelapmi.R
import com.app.jendelapmi.models.MobileUnitModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import com.app.slider.PreferenceHelper.lokasidonor
import com.app.slider.PreferenceHelper.status
import com.app.slider.PreferenceHelper.tanggalmobileunit
import kotlinx.android.synthetic.main.fragment_notifikasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotifikasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get shared preference
        val prefs = customPreference(requireContext(), "userdata")
        // get mobile unit data
        getDataJadwalMobilUnit()
        // define view component
        val txtTanggalTerakhirDonor = txt_tanggal_terakhir_donor
        val txtJadwalDonor = jadwal_big
        val txtJadwalDonorRutin = txt_jadwal_donor_rutin
        val txtLokasiDonorTerdekat = txt_lokasi_donor_terdekat
        // set text value
        txtTanggalTerakhirDonor.text = if (prefs.status == null || prefs.status != "") prefs.status else "-----"
        txtJadwalDonor.text =
            if (prefs.tanggalmobileunit != null || prefs.tanggalmobileunit != "")
                changeDateFormat(prefs.tanggalmobileunit!!
            ) else "-"
        txtJadwalDonorRutin.text = if (prefs.status == null || prefs.status != "") prefs.status else "-----"
        txtLokasiDonorTerdekat.text = prefs.lokasidonor
    }

    /**
     * change date string from yyyy-MM-dd
     * to Indonesian format dd MMMM yyyy
     */
    private fun changeDateFormat(text: String): String {
        val arrayDate = text.split("-").toTypedArray()
        val date = LocalDate.of(arrayDate[0].toInt(), arrayDate[1].toInt(), arrayDate[2].toInt())
        val formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val formattedDate = date.format(formatDate)
        return formattedDate.toString()
    }

    private fun getDataJadwalMobilUnit() {
        try {
            // instance of shared preference helper
            val prefs = customPreference(requireContext(), "userdata")
            // get data of jadwal mobile unit
            ApiService.endpoint.getMobileUnit(prefs.api_token.toString(), "pendonor")
                .enqueue(object : Callback<MobileUnitModel> {
                    override fun onResponse(
                        call: Call<MobileUnitModel>,
                        response: Response<MobileUnitModel>
                    ) {
                        val status = response.body()?.status
                        if (status == "success") {
                            if (response.body()?.data != null) {
                                // set tanggal mobile unit, lokasi donor latest
                                prefs.tanggalmobileunit =
                                    response.body()?.data!![response.body()?.data!!.size - 1].tanggal_donor
                                prefs.lokasidonor =
                                    response.body()?.data!![response.body()?.data!!.size - 1].lokasi_donor
                            }
                        }
                    }

                    override fun onFailure(call: Call<MobileUnitModel>, t: Throwable) {
                        Log.d("debug_jadwal_MU", t.toString())
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                    }

                })
        } catch (e: SocketException) {
            Toast.makeText(requireContext(), "Connection failed!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): NotifikasiFragment = NotifikasiFragment()
    }
}