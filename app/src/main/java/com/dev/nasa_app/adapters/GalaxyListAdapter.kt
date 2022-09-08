package com.dev.nasa_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.nasa_app.R
import com.dev.nasa_app.interfaces.OnItemClickListener
import com.dev.nasa_app.models.GalaxyModelWrapper


class GalaxyListAdapter(private val mNasaGalaxyList: List<GalaxyModelWrapper>,
                        private val listener: OnItemClickListener
) : RecyclerView.Adapter<GalaxyListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.nasa_rv_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {

        val mGalaxyItem = mNasaGalaxyList[pos].data[0]
        val imageUrl = mNasaGalaxyList[pos].links[0].href
        val centerAndDateCreated = holder.itemView.context.getString(R.string.center_date_splitter, mGalaxyItem.center,mGalaxyItem.dateCreated)

        Glide
            .with(holder.itemView)
            .load(imageUrl)
            .placeholder(R.drawable.no_image)
            .into(holder.imBackground);

        holder.title.text = mGalaxyItem.title
        holder.tvDateCreatedAndCenter.text = centerAndDateCreated
        holder.itemView.setOnClickListener { listener.onItemClick(mNasaGalaxyList[pos]) }

    }

    override fun getItemCount(): Int {
        return mNasaGalaxyList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val imBackground: ImageView = itemView.findViewById(R.id.imBackground)
        val tvDateCreatedAndCenter: TextView = itemView.findViewById(R.id.tvDateCreatedAndCenter)
    }
}