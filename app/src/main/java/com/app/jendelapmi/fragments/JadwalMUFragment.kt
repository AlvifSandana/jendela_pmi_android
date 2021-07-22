package com.app.jendelapmi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.jendelapmi.R
import kotlinx.android.synthetic.main.fragment_jadwal_m_u.*

class JadwalMUFragment : Fragment() {

    lateinit var button_back: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_m_u, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_back = back_btn

        button_back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    companion object {
        fun getInstance(): JadwalMUFragment = JadwalMUFragment()
    }
}