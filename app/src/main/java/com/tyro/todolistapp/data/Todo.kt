package com.tyro.todolistapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDateTime

@Entity(tableName = "todoList")
@TypeConverters(Converters::class)
data class Todo @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    val dateCreated: LocalDateTime = LocalDateTime.now()
)


object DummyList {
    @RequiresApi(Build.VERSION_CODES.O)
    val todoList = listOf(
        Todo(title="New Todo", description =  "This is a new Todo, for testing and doing other things, i want to see the max", completed = true),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = false),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = false),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = true, dateCreated = LocalDateTime.now().minusDays(1)),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = false),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = false, dateCreated = LocalDateTime.now().minusDays(2)),
        Todo(title="New Todo", description = "This is a new Todo, for testing and doing other things, i want to see the max", completed = false),
        )
}
