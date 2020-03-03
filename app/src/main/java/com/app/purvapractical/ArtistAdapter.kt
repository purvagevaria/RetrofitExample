package com.app.purvapractical

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.purvapractical.dbclasses.enitity.ArtistEntity
import com.bumptech.glide.Glide
import java.util.*

class ArtistAdapter(val myList: ArrayList<ArtistEntity>) :
    RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {
    private var arArtist = ArrayList<ArtistEntity>()

    init {
        this.arArtist = myList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_track, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvArtistName.text = arArtist[position]!!.artistName
        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, ArtistDetailActivity::class.java)
            intent.putExtra("ID", arArtist[position].artistId.toString())
            holder.itemView.context.startActivity(intent)
        }
        Glide.with(holder.imgChannel.context).load(arArtist[position].previewImage)
            .into(holder.imgChannel)

    }

    override fun getItemCount(): Int {
        return arArtist.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvArtistName: TextView
        var imgChannel: ImageView

        init {
            tvArtistName = itemView.findViewById(R.id.tvArtistName)
            imgChannel = itemView.findViewById(R.id.imgChannel)
        }
    }

}