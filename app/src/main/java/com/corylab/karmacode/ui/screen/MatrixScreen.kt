package com.corylab.karmacode.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.corylab.karmacode.R
import com.corylab.karmacode.ui.application.KarmaCode

@Composable
fun MatrixScreen(
    navController: NavController,
    data: String = ""
) {
    val day = rememberSaveable {
        mutableIntStateOf(
            data.substring(
                0,
                data.indexOfFirst { it == '.' }).toInt()
        )
    }
    val month = rememberSaveable {
        mutableIntStateOf(
            data.substring(
                data.indexOfFirst { it == '.' } + 1,
                data.indexOfLast { it == '.' }).toInt()
        )
    }
    val year = rememberSaveable {
        mutableIntStateOf(
            data.substring(
                data.indexOfLast { it == '.' } + 1,
                data.length
            ).toInt()
        )
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
        val resource = rememberSaveable {
            mutableIntStateOf(if (day.intValue > 22) sumOfDigits(day.intValue) else day.intValue)
        }
        val talent = rememberSaveable {
            mutableIntStateOf(month.intValue)
        }
        val soul = rememberSaveable {
            mutableIntStateOf(sumOfDigits(year.intValue))
        }
        Text(text = "Ресурс: ${resource.intValue}")
        /*TODO: Конечно всё на сервере, это просто проверка*/
        Text(text = getInfoWithKey(R.raw.resource, resource.intValue))
        Text(text = "Талант: ${talent.intValue}")
        /*TODO: Конечно всё на сервере, это просто проверка*/
        Text(text = getInfoWithKey(R.raw.talent, talent.intValue))
        Text(text = "Задача души: ${soul.intValue}")
        /*TODO: Конечно всё на сервере, это просто проверка*/
        Text(text = getInfoWithKey(R.raw.soul, soul.intValue))
    }
}

fun sumOfDigits(n: Int) = n.toString().map { "$it".toInt() }.sum()

/*TODO: Конечно всё на сервере, это просто проверка*/
fun getInfoWithKey(id: Int, key: Int): String {
    val rawText = KarmaCode.context.resources.openRawResource(id).bufferedReader().use { it.readText() }
    rawText.lines().forEachIndexed { index, line ->
        if (line.toIntOrNull() == key) {
            return rawText.lines()[index + 1]
        }
    }
    return ""
}