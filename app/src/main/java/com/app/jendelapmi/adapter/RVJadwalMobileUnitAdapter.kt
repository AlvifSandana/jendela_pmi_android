package com.app.jendelapmi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.jendelapmi.R
import com.app.jendelapmi.models.MobileUnitModel
import kotlinx.android.synthetic.main.item_table.view.*

class RVJadwalMobileUnitAdapter(private val data: ArrayList<MobileUnitModel.Data>) :
    RecyclerView.Adapter<RVJadwalMobileUnitAdapter.RVJadwalMobileUnitViewHolder>() {

    class RVJadwalMobileUnitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_tanggal: TextView = itemView.col_tanggal
        val txt_jam: TextView = itemView.col_jam
        val txt_instansi: TextView = itemView.col_instansi
        val txt_tempat: TextView = itemView.col_tempat
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVJadwalMobileUnitViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_table,
            parent, false
        )

        return RVJadwalMobileUnitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVJadwalMobileUnitViewHolder, position: Int) {
        val currentItem = data[position]

        holder.txt_tanggal.text = currentItem.tanggal_donor
        holder.txt_jam.text = currentItem.waktu_mulai
        holder.txt_instansi.text = currentItem.lokasi_donor
        holder.txt_tempat.text = currentItem.deskripsi
    }

    override fun getItemCount() = data.size
}