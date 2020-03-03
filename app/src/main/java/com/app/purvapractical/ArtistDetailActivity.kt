package com.app.purvapractical

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.purvapractical.dbclasses.AppDatabase
import com.app.purvapractical.dbclasses.enitity.ArtistEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_artist_detail.*

class ArtistDetailActivity : AppCompatActivity(), RecyclerItemClickListener {
    var artistBean = ArrayList<ArtistEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)

        val id = intent.getStringExtra("ID").toInt()


        artistBean =
            AppDatabase.getInstance(this)!!.daoArtist().getSingleArtist(id) as ArrayList<ArtistEntity>

        Glide.with(this).load(artistBean[0].previewImage)
            .into(imgBanner)

        rvSubList.adapter = ArtistSubListAdapter(artistBean as ArrayList<ArtistEntity>, this)
    }

    override fun onItemClick(view: View?, position: Int) {
        view!!.alpha = 0.1f
        if (artistBean[position].isPlaying) { //Pause media
            artistBean.map { it.isPlaying = false }
            rvSubList.adapter!!.notifyDataSetChanged()

        } else { //Play media
            artistBean.map { it.isPlaying = false }
            rvSubList.adapter!!.notifyDataSetChanged()
            artistBean[position].isPlaying = true
            rvSubList.adapter!!.notifyItemChanged(position)

        }
        view!!.alpha = 1f

    }

    override fun onChildClick(view: View?, position: Int, data: Any) {

    }

    override fun onPause() {
        super.onPause()


    }
}
