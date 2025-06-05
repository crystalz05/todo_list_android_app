package com.tyro.todolistapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyro.todolistapp.R
import com.tyro.todolistapp.data.Todo
import com.tyro.todolistapp.data.getFormattedDate
import com.tyro.todolistapp.viewModel.TodoViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(viewModel: TodoViewModel) {

    val isVisible = remember { mutableStateOf(false) }
    val currentEditingId = remember { mutableLongStateOf(0L) }
    val todos = viewModel.getAllTodos.collectAsState(initial = listOf())


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Plan", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(painter = painterResource(id = R.drawable.baseline_density_medium_24), contentDescription = "")
                    }
                }

                , actions = {
                    Row {
                        IconButton(onClick = {}) {
                            Icon(painter = painterResource(id = R.drawable.baseline_notifications_24),
                                contentDescription = "", tint = colorResource(id = R.color.purple_700)

                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(20.dp),
                    onClick = {
                        // Handle FAB click here
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_task_24),
                        contentDescription = "Add"
                    )
                }

        }



        ){ innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Todo Items", fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 30.dp))
            Column(modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp
                    ), clip = true
                )
                .fillMaxSize()
                .background(color = Color.White)) {

                HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(top = 20.dp))
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {

                    items(todos.value) {
                        todo ->
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = {
                                if(it == SwipeToDismissBoxValue.EndToStart){
                                    //TODO()

                                    false
                                }else false
                            }
                        )
                        SwipeToDismissBox(
                            state = dismissState, backgroundContent = {

                            },
                            content = {
                                TodoItem({
                                    isVisible.value = true
                                    currentEditingId.value = todo.id
                                         }, todo)
                            }
                        )
                    }

                }

            }
//            if(isVisible.value){
//                ConfirmCompletion(onClicked = {isVisible.value = false},
//                    todos.find { it.id == currentEditingId.value })
//            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoItem(
    onClicked: ()-> Unit,
    todo: Todo
){
    val isChecked = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth().clickable {onClicked()}
        .padding(8.dp).padding(horizontal = 13.dp)
        .background(color = if(todo.completed) colorResource(id = R.color.light_green).copy(alpha = 0.3f)
            else colorResource(id = R.color.light_orange).copy(alpha = 0.3f)
            , shape = RoundedCornerShape(16.dp))
        .border(2.dp, color = if(todo.completed) colorResource(id = R.color.green_100).copy(alpha = 0.5f) else
            colorResource(id = R.color.orange_100).copy(alpha = 0.5f)
            , shape = RoundedCornerShape(16.dp))
        .padding(16.dp)



        ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {


            Column(modifier = Modifier.fillMaxHeight().weight(0.5f), verticalArrangement = Arrangement.Bottom) {
                if(todo.completed){
                    Icon(
                        modifier = Modifier.size(30.dp),
                        tint = colorResource(id = R.color.green_500),
                        painter = painterResource(id = R.drawable.baseline_task_alt_24), contentDescription = "")
                }else{
                    Icon(
                        modifier = Modifier.size(30.dp),
                        tint = colorResource(id = R.color.orange),
                        painter = painterResource(id = R.drawable.baseline_hourglass_bottom_24), contentDescription = "")
                }
            }

            Column(modifier = Modifier.weight(2f),
                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(todo.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(todo.description, fontSize = 14.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(getFormattedDate(todo.dateCreated), color = Color.Gray)

            }
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(id = R.drawable.baseline_edit_24), contentDescription = "")
                }
            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TodoListScreenPreview(){
    TodoListScreen(viewModel = TodoViewModel())
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun TodoItemPreview(){
//    val todo: Todo = Todo("3", "New Todo",
//        "This is a new Todo, for testing and doing other things, i want to see the max",
//        listOf("Start Walking", "Stop Walking", "Jump", "land"), false)
//    TodoItem({}, todo)
//}