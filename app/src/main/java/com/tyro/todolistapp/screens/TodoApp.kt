package com.tyro.todolistapp.screens


import android.app.Application
import com.tyro.todolistapp.data.Graph

class TodoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}