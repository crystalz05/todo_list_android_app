package com.tyro.todolistapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    fun getTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

    fun getTodoById(id: Long): Flow<Todo> = todoDao.getTodoById(id)

    suspend  fun toggleIsCompleted(id: Long){
        todoDao.toggleTodoCompleted(id)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }

}