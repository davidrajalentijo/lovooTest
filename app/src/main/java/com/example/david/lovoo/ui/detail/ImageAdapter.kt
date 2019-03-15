package com.example.david.lovoo.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.david.lovoo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_images.view.*

/*
    Adapter to manage a list of images
 */
class ImageAdapter(val images: List<String>) : RecyclerView.Adapter<ImageAdapter.ImagesAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_images, parent, false)
        return ImagesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImagesAdapterViewHolder, position: Int) {
        holder?.bindRoom(position)
    }

    inner class ImagesAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindRoom(position: Int) {
            val image = images[position]
            itemView.apply {
                Picasso.get().load(image).into(room_image)
            }
        }
    }

}