package com.example.sprintm6.model
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PhoneDao {
    @Query("SELECT * FROM TABLE_PHONE ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Phone>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPhones(listCourses: List<Phone>)

    @Query("DELETE FROM TABLE_PHONE")
    suspend fun deleteAll()

    @Query("DELETE FROM TABLE_PHONE where id=:id")
    suspend fun deleteUno(id: Int)
}



