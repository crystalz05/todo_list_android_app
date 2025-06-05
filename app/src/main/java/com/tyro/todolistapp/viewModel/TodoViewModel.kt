package com.tyro.todolistapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyro.todolistapp.data.Graph
import com.tyro.todolistapp.data.Todo
import com.tyro.todolistapp.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoRepository: TodoRepository = Graph.todoRepository
): ViewModel() {

//    lateinit var getAllTodos: Flow<List<Todo>>
//
//    init {
//        viewModelScope.launch {
//            getAllTodos = todoRepository.getTodos()
//        }
//    }

    val getAllTodos: Flow<List<Todo>> = todoRepository.getTodos()


    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addTodo(todo)
        }
    }


    fun updtateTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.deleteTodo(todo)
        }
    }

    fun getTodoById(id: Long): Flow<Todo> = todoRepository.getTodoById(id)

}