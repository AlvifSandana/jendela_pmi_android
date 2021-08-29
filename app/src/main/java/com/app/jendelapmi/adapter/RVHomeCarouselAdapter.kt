package com.app.jendelapmi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.jendelapmi.R
import com.app.jendelapmi.models.CarouselHomeModel
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.carousel_item.view.*

class RVHomeCarouselAdapter(private val imageURL: ArrayList<CarouselHomeModel>): RecyclerView.Adapter<RVHomeCarouselAdapter.RVHomeViewHolder>() {
    class RVHomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageContainer: RoundedImageView = itemView.image_container
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return RVHomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVHomeViewHolder, position: Int) {
        val currentItem = imageURL[position]
        Picasso.get().load(currentItem.url).resize(300, 300).centerCrop().into(holder.imageContainer)
    }

    override fun getItemCount(): Int = imageURL.size
}