package com.corylab.karmacode.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController

@Composable
fun CalculationScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        val day = rememberSaveable { mutableStateOf("") }
        val month = rememberSaveable { mutableStateOf("") }
        val year = rememberSaveable { mutableStateOf("") }
        TextField(
            value = day.value,
            onValueChange = { day.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Число рождения") })
        TextField(
            value = month.value,
            onValueChange = { month.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Месяц рождения") })
        TextField(
            value = year.value,
            onValueChange = { year.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Год рождения") })
        Box(modifier = Modifier.fillMaxSize()) {
            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                val sum = StringBuilder().
                append(day.value).append(".").
                append(month.value).append(".").
                append(year.value).toString()
                navController.navigate("matrix/${sum}")
            }) {
                Text(text = "Рассчитать")
            }
        }
    }
}