package com.tyro.todolistapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyro.todolistapp.data.Todo

@Composable
fun AddUpdateTodo(id: Long){


    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = title.value,
            onValueChange = {title.value = it},
            label = { Text("Title")},
            textStyle = TextStyle(fontSize = 18.sp)
            )

        OutlinedTextField(
            value = description.value,
            onValueChange = {description.value = it},
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().height(400.dp),
            singleLine = false,
            maxLines = 100,
            textStyle = TextStyle(fontSize = 18.sp)
        )
        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(all = 44.dp))

        Button(onClick = {}, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(3.dp)) {
            Text("Add New Todo")
        }

    }





}

@Preview(showBackground = true)
@Composable
fun AddNewTodoPreview(){

    AddUpdateTodo(id = 2L)

}