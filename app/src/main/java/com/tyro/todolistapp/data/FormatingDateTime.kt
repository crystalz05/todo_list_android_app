package com.tyro.todolistapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
private fun formatDate(dateTime: LocalDateTime): String{
    val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy", Locale.ENGLISH)
    return formatter.format(dateTime)
}



@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(dateTime: LocalDateTime): String{

    if(dateTime.toLocalDate() == LocalDate.now()){
        return "Today"
    }
    else if(dateTime.toLocalDate().plusDays(1) == LocalDate.now()){
        return "Yesterday"
    }
    else return formatDate(dateTime)
}