package com.app.purvapractical.dbclasses.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
class ArtistEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "artist_id")
    var artistId: Int = 0
    @ColumnInfo(name = "artist_name")
    var artistName: String = ""
    @ColumnInfo(name = "track_name")
    var trackName: String = ""
    @ColumnInfo(name = "collection_name")
    var collectionName: String = ""
    @ColumnInfo(name = "trackCensoredName")
    var trackCensoredName: String = ""
    @ColumnInfo(name = "artist_image")
    var previewImage: String = ""
    @ColumnInfo(name = "category")
    var category: String = ""
    @ColumnInfo(name = "categoryCount")
    var countCount: Int = 0
    @ColumnInfo(name = "preview_url")
    var previewUrl: String = ""
    var isPlaying = false

}