package com.app.jendelapmi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.jendelapmi.R

class StokDarahFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stok_darah, container, false)
    }

    companion object {
        fun getInstance(): StokDarahFragment = StokDarahFragment()
    }
}