package com.app.purvapractical.dbclasses

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.app.purvapractical.dbclasses.daoclasses.DaoArtist
import com.app.purvapractical.dbclasses.enitity.ArtistEntity


@Database(
    entities = arrayOf(
        ArtistEntity::class
    ),
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoArtist(): DaoArtist

    companion object {
        private const val DB_NAME = "purvaapp.db"

        @Volatile
        var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context?): AppDatabase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        fun create(context: Context?): AppDatabase? {
            return Room.databaseBuilder<AppDatabase>(context!!, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries().build()
        }
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {

    }
}