package com.techcareer.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techcareer.todoapp.data.entity.Yapilacaklar

@Database(entities = [Yapilacaklar::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun getYapilacaklarDao() : YapilacaklarDao
}