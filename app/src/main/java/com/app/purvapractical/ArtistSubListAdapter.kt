package com.app.purvapractical

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.purvapractical.dbclasses.enitity.ArtistEntity
import java.util.*

class ArtistSubListAdapter(
    myList: ArrayList<ArtistEntity>,
    listener: RecyclerItemClickListener
) :
    RecyclerView.Adapter<ArtistSubListAdapter.MyViewHolder>() {
    private var arSearch = ArrayList<ArtistEntity>()
    private var listener: RecyclerItemClickListener? = null

    init {
        this.arSearch = myList
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_sublist, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvArtistName.text = arSearch[position]!!.trackCensoredName
        holder.tvCategoryCount.text = arSearch[position]!!.category
        holder.tvDisplayName.text = arSearch[position]!!.collectionName
        holder.imgPlay.isEnabled = false

        if (arSearch[position].isPlaying) {

            holder.imgPlay.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.imgPlay.context,
                    R.drawable.img_play
                )
            )
        } else {
            holder.imgPlay.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.imgPlay.context,
                    R.drawable.ic_pause
                )
            )
        }
        holder.itemView.setOnClickListener {
            listener!!.onItemClick(holder.imgPlay, position)
        }

    }

    override fun getItemCount(): Int {
        return arSearch.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvArtistName: TextView
        var tvDisplayName: TextView
        var tvCategoryCount: TextView
        var imgPlay: ImageView

        init {
            tvArtistName = itemView.findViewById(R.id.tvArtistName)
            tvDisplayName = itemView.findViewById(R.id.tvDisplayName)
            tvCategoryCount = itemView.findViewById(R.id.tvCategoryCount)
            imgPlay = itemView.findViewById(R.id.imgPlay)
        }
    }

}