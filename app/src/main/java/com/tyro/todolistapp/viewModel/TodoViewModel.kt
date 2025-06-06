package com.tyro.todolistapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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


    var todoTitleState by mutableStateOf("")
    var todoDescriptionState by mutableStateOf("")

    fun onTodoTitleChanged(newString: String){
        todoTitleState = newString
    }

    fun onTodoDescriptionChange(newString: String){
        todoDescriptionState = newString
    }

    val getAllTodos: Flow<List<Todo>> = todoRepository.getTodos()


    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addTodo(todo)
        }
    }

    fun toggleTodo(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.toggleIsCompleted(id)
        }
    }


    fun updateTodo(todo: Todo){
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