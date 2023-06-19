package com.bookmyshow.feature_one.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.domain_layer.model.VenuesModel
import com.bookmyshow.feature_one.databinding.VenueItemBinding

class VenueAdapter(private val venueList: List<VenuesModel>, private val loader: ImageLoader) :
    Adapter<VenueAdapter.VenueHolder>() {

    inner class VenueHolder(val binding: VenueItemBinding) : ViewHolder(binding.root)

    private var itemClick: IClick? = null

    interface IClick {
        fun onClick(id: Int)
    }

    fun registerClicks(click: IClick) {
        this.itemClick = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueHolder {
        return VenueHolder(VenueItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = venueList.size

    override fun onBindViewHolder(holder: VenueHolder, position: Int) {
        holder.binding.venue = venueList[position]
        holder.binding.imageLoader = loader
        holder.binding.url =
            "https://static.businessworld.in/article/article_extra_large_image/1609147522_O1aw88_BMS.jpg"
        holder.binding.root.setOnClickListener {
            itemClick?.onClick(position)
        }
    }
}