package com.tyro.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Todo::class], version = 1, exportSchema = false
)

abstract class TodoDataBase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}

