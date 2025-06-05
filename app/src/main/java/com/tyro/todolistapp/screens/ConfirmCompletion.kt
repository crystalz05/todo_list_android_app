package com.tyro.todolistapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tyro.todolistapp.data.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmCompletion(onClicked: ()-> Unit, todo: Todo?) {
    BasicAlertDialog(
        modifier = Modifier.statusBarsPadding().navigationBarsPadding(),
        onDismissRequest = {onClicked()}, content = {

            Column(modifier = Modifier.fillMaxWidth().height(600.dp)
                .background(color = Color.Blue),


                ) {
                Button(onClick = {}) {

                }

                Text("Completed or Not")
                if (todo != null) {
                    Text(todo.description)
                }
            }

        })
}