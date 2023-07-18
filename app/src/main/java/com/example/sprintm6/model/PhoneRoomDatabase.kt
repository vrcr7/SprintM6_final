package com.example.sprintm6.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Phone::class), version = 1, exportSchema = false)
abstract class PhoneRoomDatabase : RoomDatabase() {
    abstract fun PhoneDao(): PhoneDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PhoneRoomDatabase? = null

        fun getDatabase(context: Context): PhoneRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneRoomDatabase::class.java,
                    "phone_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}