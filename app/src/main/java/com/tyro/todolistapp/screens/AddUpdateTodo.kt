package com.tyro.todolistapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tyro.todolistapp.R
import com.tyro.todolistapp.data.Todo
import com.tyro.todolistapp.viewModel.TodoViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUpdateTodo(viewModel: TodoViewModel, navController: NavController, todoId: Long ){

    val context = LocalContext.current
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val todoUpdate = remember { mutableStateOf<Todo?>(null) }

    if (todoId != 0L){
        val todo = viewModel.getTodoById(todoId).collectAsState(initial = Todo(0L, title = "", description = ""))
        viewModel.todoTitleState = todo.value.title
        viewModel.todoDescriptionState = todo.value.description
    }else{
        viewModel.todoTitleState = ""
        viewModel.todoDescriptionState = ""
    }

//
//    if (todoId != 0L){
//        LaunchedEffect(todoId) {
//            val todoState =  viewModel.getTodoById(id = todoId).first()
//            todoUpdate.value = todoState
//            title.value = todoState.title
//            description.value = todoState.description
//        }
//    }

    Scaffold(modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.purple_200)),
                title = { Text(if(todoId == 0L)"Add New Todo" else "Update Todo", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }

        ) { innerPadding ->

        Column(modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp)
            .background(color = Color.White),

            ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = viewModel.todoTitleState,
                onValueChange = {viewModel.onTodoTitleChanged(it)},
                label = { Text("Title")},
                textStyle = TextStyle(fontSize = 18.sp)
            )

            OutlinedTextField(
                value = viewModel.todoDescriptionState,
                onValueChange = {viewModel.onTodoDescriptionChange(it)},
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth().height(400.dp),
                singleLine = false,
                maxLines = 100,
                textStyle = TextStyle(fontSize = 18.sp)
            )
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(all = 44.dp))

            Button(onClick = {

                if(viewModel.todoTitleState.isNotBlank() && viewModel.todoDescriptionState.isNotBlank()){
                    if(todoId != 0L){
                        viewModel.updateTodo(
                            Todo(
                                id = todoId,
                                title = viewModel.todoTitleState,
                                description = viewModel.todoDescriptionState
                            )
                        )
                    }else{
                        val newTodo = Todo(
                            title =viewModel.todoTitleState,
                            description = viewModel.todoDescriptionState
                        )
                        viewModel.addTodo(newTodo)
                    }
                    navController.navigateUp()
                }else{
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_LONG).show()
                }


            }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(3.dp)) {
                Text(text= if(todoId == 0L)"Add New Todo" else "Update Todo")
            }

        }

    }

}
