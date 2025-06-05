package com.tyro.todolistapp.data

import android.content.Context
import androidx.room.Room

object Graph {

    lateinit var dataBase: TodoDataBase

    val todoRepository by lazy {
        TodoRepository(todoDao = dataBase.todoDao())
    }

    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, TodoDataBase::class.java, "todolist.db").build()
    }

}