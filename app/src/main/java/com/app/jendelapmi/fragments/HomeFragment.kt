package com.app.jendelapmi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.jendelapmi.R
import com.app.jendelapmi.helpers.AlertHelper
import com.app.slider.PreferenceHelper.api_token
import com.app.slider.PreferenceHelper.customPreference
import kotlinx.android.synthetic.main.fragment_home.*
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselType


class HomeFragment : Fragment() {
    lateinit var button_stok_darah: Button
    lateinit var button_mobile_unit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // define buttons
        button_stok_darah = btn_stok_darah
        button_mobile_unit = btn_mobile_unit

        // define the carousel
        val carousel: ImageCarousel = carousel
        carousel.registerLifecycle(lifecycle)

        // carousel contents
        val list = mutableListOf<CarouselItem>()
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1593642702821-c8da6771f0c6?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=889&q=80",
            )
        )
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1626233127008-5431435e7a57?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1498&q=80"
            )
        )
        list.add(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1497215728101-856f4ea42174?ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80"
            )
        )

        // carousel settings
        carousel.showTopShadow = false
        carousel.showBottomShadow = false
        carousel.showNavigationButtons = false
        carousel.carouselType = CarouselType.SHOWCASE
        carousel.scaleOnScroll = true
        carousel.carouselGravity = CarouselGravity.CENTER
        carousel.imagePlaceholder = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_outline_image_24
        )

        // set carousel data
        carousel.setData(list)

        // button listener
        button_stok_darah.setOnClickListener {
            val pref = customPreference(requireContext(), "userdata")
            if (pref.api_token != "") gotoFragment(StokDarahFragment()) else AlertHelper.createAlert(
                requireContext(),
                "Info",
                "Silahkan login untuk melanjutkan."
            )
        }
    }

    private fun gotoFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.rootFragment, fragment)
        transaction.commit()
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}