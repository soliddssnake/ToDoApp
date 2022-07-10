package com.techcareer.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.techcareer.todoapp.data.entity.Yapilacaklar

@Dao
interface YapilacaklarDao {

    @Query("SELECT * FROM yapilacaklar")
    suspend fun tumYapilacaklar(): List<Yapilacaklar>

    @Insert
    suspend fun yapilacakEkle(yapilacak: Yapilacaklar)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%' || :aramaKelimesi || '%' ")
    suspend fun yapilacaklarArama(aramaKelimesi:String): List<Yapilacaklar>

    @Update
    suspend fun yapilacakGuncelle(yapilacak: Yapilacaklar)

    @Delete
    suspend fun yapilacakSil(yapilacak: Yapilacaklar)

}