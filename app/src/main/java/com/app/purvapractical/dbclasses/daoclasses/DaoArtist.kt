package com.app.purvapractical.dbclasses.daoclasses

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.purvapractical.dbclasses.enitity.ArtistEntity

@Dao
interface DaoArtist {
    @Query("SELECT * FROM artist GROUP BY artist_id")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM artist where artist_id=:id")
    fun getSingleArtist(id: Int): List<ArtistEntity>

    @Insert
    fun insert(productData: ArtistEntity)

    @Query("DELETE FROM artist")
    fun truncateArtist()

}