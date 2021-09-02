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

        val images: ArrayList<CarouselExploreUDDModel> = arrayListOf()
        images.add(
            CarouselExploreUDDModel("https://images.unsplash.com/photo-1626948683643-bcf4aaf91829?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1400&q=80")
        )
        images.add(
            CarouselExploreUDDModel("https://images.unsplash.com/photo-1598805858343-5722efd414ca?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=466&q=80")
        )
        images.add(
            CarouselExploreUDDModel("https://images.unsplash.com/photo-1504297050568-910d24c426d3?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80")
        )
        images.add(
            CarouselExploreUDDModel("https://images.unsplash.com/photo-1625456824710-dd9eac85d5df?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80")
        )
        images.add(
            CarouselExploreUDDModel("https://images.unsplash.com/photo-1626897844971-aef92643f056?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=375&q=80")
        )

        // Carousel explore UDD configuration
        exploreUDDCarousel = vp2
        exploreUDDCarousel.adapter = RVExploreUDDAdapter(images)
        exploreUDDCarousel.clipToPadding = false
        exploreUDDCarousel.clipChildren = false
        exploreUDDCarousel.offscreenPageLimit = 3
        exploreUDDCarousel.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Carousel more info configuration
        moreinfoUDDCarousel = vp2_moreinfo
        moreinfoUDDCarousel.adapter = RVExploreUDDAdapter(images)
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