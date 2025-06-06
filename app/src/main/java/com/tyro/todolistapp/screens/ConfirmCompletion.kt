package com.tyro.todolistapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyro.todolistapp.data.Todo
import kotlinx.coroutines.flow.Flow
import com.tyro.todolistapp.R
import com.tyro.todolistapp.viewModel.TodoViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmCompletion(viewModel: TodoViewModel, onClicked: ()-> Unit, todoFlow: Flow<Todo>) {

    val todoState = todoFlow.collectAsState(initial = Todo())
    val scrollState = rememberScrollState()


    BasicAlertDialog(

        modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        onDismissRequest = {onClicked()}, content = {

            Column(modifier = Modifier.fillMaxWidth().height(600.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                ) {

                Column(Modifier.fillMaxWidth().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(painter = painterResource(
                        id = if(todoState.value.completed) R.drawable.baseline_task_alt_24
                        else R.drawable.baseline_access_time_filled_24
                    ),
                        contentDescription = "",
                        Modifier.size(150.dp),
                        tint = if(todoState.value.completed)colorResource(id = R.color.green_700)
                        else colorResource(id = R.color.orange_500)
                    )
                }

                Column(Modifier.fillMaxWidth().padding(horizontal = 45.dp).weight(1f)){
                    Text(todoState.value.title, fontWeight = FontWeight.Bold, fontSize = 21.sp)
                    HorizontalDivider(Modifier.padding(top = 14.dp), thickness = 1.dp)
                    Text(text = todoState.value.description, Modifier.verticalScroll(scrollState).padding(top = 14.dp), fontWeight = FontWeight.Normal, fontSize = 18.sp)


                }

                Column(Modifier.fillMaxWidth().padding(45.dp)){
                    Button(onClick = {viewModel.toggleTodo(todoState.value.id)}, Modifier.fillMaxWidth()) {
                        if(todoState.value.completed){
                            Text("Not Completed")
                        }else{
                            Text("Completed")
                        }
                    }
                    Button(onClick = {onClicked()}, Modifier.fillMaxWidth()) {
                        Text("Close")
                    }
                }


            }

        })
}