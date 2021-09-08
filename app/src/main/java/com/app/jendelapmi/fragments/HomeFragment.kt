package com.app.jendelapmi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.jendelapmi.R
import com.app.jendelapmi.adapter.RVHomeCarouselAdapter
import com.app.jendelapmi.adapter.RVKegiatanPMI
import com.app.jendelapmi.helpers.AlertHelper
import com.app.jendelapmi.models.CarouselHomeModel
import com.app.jendelapmi.models.HomeModel
import com.app.jendelapmi.retrofit.ApiService
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.math.abs


class HomeFragment : Fragment() {
    lateinit var button_stok_darah: Button
    lateinit var button_mobile_unit: Button
    lateinit var rvKegiatanPMI: RecyclerView
    lateinit var homeCarousel: ViewPager2
    lateinit var txtLihatLainnya: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = customPreference(requireContext(), "userdata")

        // define view components
        button_stok_darah = btn_stok_darah
        button_mobile_unit = btn_mobile_unit
        rvKegiatanPMI = rv_home_kegiatan_pmi
        txtLihatLainnya = lihat_lainnya

        // button listener
        button_stok_darah.setOnClickListener {
            if (pref.api_token != "") gotoFragment(StokDarahFragment()) else AlertHelper.createAlert(
                requireContext(),
                "Info",
                "Silahkan login untuk melanjutkan."
            )
        }
        button_mobile_unit.setOnClickListener {
            gotoFragment(JadwalMUFragment())
        }

        // lihat lainnya listener
        txtLihatLainnya.setOnClickListener {
            gotoFragment(KegiatanFragment())
        }

        // set array list of images for carousel
        val images: ArrayList<CarouselHomeModel> = arrayListOf()
        images.add(
            CarouselHomeModel("http://192.168.0.101:8000/images/uploaded/moreinfo/11.jpeg")
        )
        images.add(
            CarouselHomeModel("http://192.168.0.101:8000/images/uploaded/moreinfo/12.jpeg")
        )
        images.add(
            CarouselHomeModel("http://192.168.0.101:8000/images/uploaded/moreinfo/13.jpeg")
        )

        // settings for carousel
        homeCarousel = carouselHome
        homeCarousel.adapter = RVHomeCarouselAdapter(images)
        homeCarousel.clipToPadding = false
        homeCarousel.clipChildren = false
        homeCarousel.offscreenPageLimit = 3
        homeCarousel.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // composite page transformer
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        homeCarousel.setPageTransformer(compositePageTransformer)
        getKegiatanPMI()
    }

    /**
     * get data kegiatan from api
     */
    private fun getKegiatanPMI() {
        try {
            // instance of shared preference helper
            val prefs = customPreference(requireContext(), "userdata")
            // get data kegiatan
            ApiService.endpoint.getKegiatan(prefs.api_token.toString(), "pendonor")
                .enqueue(object : Callback<HomeModel> {
                    override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        if (status == "success") {
                            if (data != null) {
                                // set rv adapter and layout manager
                                rvKegiatanPMI.adapter = RVKegiatanPMI(data)
                                rvKegiatanPMI.layoutManager = LinearLayoutManager(context)
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

    /**
     * go to another fragment
     */
    private fun gotoFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.rootFragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}