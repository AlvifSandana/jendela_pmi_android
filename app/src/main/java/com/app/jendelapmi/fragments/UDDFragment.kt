package com.app.jendelapmi.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.jendelapmi.R
import com.app.jendelapmi.adapter.RVExploreUDDAdapter
import com.app.jendelapmi.models.CarouselExploreUDDModel
import com.app.jendelapmi.models.CarouselHomeModel
import kotlinx.android.synthetic.main.fragment_udd.*
import kotlin.math.abs

class UDDFragment : Fragment() {

    lateinit var exploreUDDCarousel: ViewPager2
    lateinit var moreinfoUDDCarousel: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_udd, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define array list of images url
        val imagesUDD: ArrayList<CarouselExploreUDDModel> = arrayListOf()
        val imagesMoreInfo: ArrayList<CarouselExploreUDDModel> = arrayListOf()
        imagesUDD.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/udd/1.jpg")
        )
        imagesUDD.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/udd/2.jpg")
        )
        imagesUDD.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/udd/3.jpeg")
        )
        imagesUDD.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/udd/4.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/1.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/2.jpg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/3.jpg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/5.jpg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/6.jpg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/7.jpg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/8.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/9.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/10.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/11.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/12.jpeg")
        )
        imagesMoreInfo.add(
            CarouselExploreUDDModel("http://192.168.0.101:8000/images/uploaded/moreinfo/13.jpeg")
        )


        // Carousel explore UDD configuration
        exploreUDDCarousel = vp2
        exploreUDDCarousel.adapter = RVExploreUDDAdapter(imagesUDD)
        exploreUDDCarousel.clipToPadding = false
        exploreUDDCarousel.clipChildren = false
        exploreUDDCarousel.offscreenPageLimit = 3
        exploreUDDCarousel.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Carousel more info configuration
        moreinfoUDDCarousel = vp2_moreinfo
        moreinfoUDDCarousel.adapter = RVExploreUDDAdapter(imagesMoreInfo)
        moreinfoUDDCarousel.clipToPadding = false
        moreinfoUDDCarousel.clipChildren = false
        moreinfoUDDCarousel.offscreenPageLimit = 3
        moreinfoUDDCarousel.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // composite page transformer
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        // set carousel page transformer
        exploreUDDCarousel.setPageTransformer(compositePageTransformer)
        moreinfoUDDCarousel.setPageTransformer(compositePageTransformer)
    }

    companion object {
        fun getInstance(): UDDFragment = UDDFragment()
    }
}