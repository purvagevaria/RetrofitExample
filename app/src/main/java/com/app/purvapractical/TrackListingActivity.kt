package com.app.purvapractical

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.purvapractical.dbclasses.AppDatabase
import com.app.purvapractical.dbclasses.enitity.ArtistEntity
import com.app.purvapractical.webservices.RestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_track.*


class TrackListingActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)


        imgProgress.visibility = View.VISIBLE

        if (isNetworkAvailable()) {

            val subsription =
                RestClient.get().callWebservice()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            it.resultCount
                            val array = it.results as ArrayList
                            AppDatabase.getInstance(this)!!.daoArtist().truncateArtist()

                            for (data in array) {
                                val artistEntity = ArtistEntity()
                                artistEntity.artistName = data.artistName!!
                                artistEntity.trackName = data.trackName!!
                                artistEntity.collectionName = data.collectionName!!
                                artistEntity.trackCensoredName = data.trackCensoredName!!
                                artistEntity.previewImage = data.artworkUrl100!!
                                artistEntity.artistId = data.artistId!!
                                artistEntity.category = data.primaryGenreName!!
                                artistEntity.previewUrl = data.previewUrl!!
                                AppDatabase.getInstance(this)!!.daoArtist().insert(artistEntity)
                            }
                            val arArtist =
                                AppDatabase.getInstance(this)!!.daoArtist().getAll() as ArrayList
                            rvSearch.adapter = ArtistAdapter(arArtist)
                            imgProgress.visibility = View.GONE

                        }) { error: Throwable ->
                        error.printStackTrace()
                    }

            disposable.add(subsription)
        } else {
            Toast.makeText(this, getString(R.string.internet_not_available), Toast.LENGTH_LONG)
                .show()
            finish()
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

}
