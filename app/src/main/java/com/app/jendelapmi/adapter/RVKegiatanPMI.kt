package com.app.jendelapmi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.jendelapmi.R
import com.app.jendelapmi.models.HomeModel
import com.app.jendelapmi.models.KegiatanPMIModel
import kotlinx.android.synthetic.main.item_kegiatan.view.*

class RVKegiatanPMI(private val data: ArrayList<HomeModel.Data>) :
    RecyclerView.Adapter<RVKegiatanPMI.KegiatanPMIViewHolder>() {

    /**
     * View Holder Class
     *
     * define components of item view
     */
    class KegiatanPMIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanggalKegiatan: TextView = itemView.txt_tanggal_kegiatan
        val judulKegiatan: TextView = itemView.txt_judul_kegiatan
    }

    /**
     * Create layout inflater for recycler view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanPMIViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_kegiatan, parent, false)
        return KegiatanPMIViewHolder(itemView)
    }

    /**
     * Binding data with item view via view holder
     */
    override fun onBindViewHolder(holder: KegiatanPMIViewHolder, position: Int) {
        val currentItem = data[position]

        holder.tanggalKegiatan.text = currentItem.tanggal_kegiatan
        holder.judulKegiatan.text = currentItem.nama_kegiatan
    }

    /**
     * count size of data
     */
    override fun getItemCount(): Int = data.size
}