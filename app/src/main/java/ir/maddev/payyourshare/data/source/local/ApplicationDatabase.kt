package ir.maddev.payyourshare.data.source.local

import androidx.room.RoomDatabase

abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun shareDao(): ShareDao
}