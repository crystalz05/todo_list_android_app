package com.tyro.todolistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun addTodo(todoEntity: Todo)

    @Query("Select * from `todoList`")
    abstract fun getAllTodos(): Flow<List<Todo>>

    @Query("update `todoList` SET completed = NOT completed WHERE id = :id")
    abstract suspend fun toggleTodoCompleted(id: Long)

    @Update
    abstract suspend fun updateTodo(todoEntity: Todo)

    @Delete
    abstract suspend fun deleteTodo(todoEntity: Todo)

    @Query("SELECT * from `todoList` WHERE id = :id")
    abstract fun getTodoById(id: Long): Flow<Todo>

}