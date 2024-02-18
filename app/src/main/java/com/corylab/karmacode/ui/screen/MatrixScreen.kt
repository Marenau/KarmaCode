package com.corylab.karmacode.ui.screen

import android.R.attr.text
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Align
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val resource = rememberSaveable {
        mutableIntStateOf(if (day.intValue > 22) sumOfDigits(day.intValue) else day.intValue)
    }
    val talent = rememberSaveable {
        mutableIntStateOf(month.intValue)
    }
    val soul = rememberSaveable {
        mutableIntStateOf(sumOfDigits(year.intValue))
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
//        Image(
//            imageVector = ImageVector.vectorResource(id = R.drawable.matrix),
//            contentDescription = null,
//            modifier = Modifier
//                .drawWithContent {
//                    drawContent()
//                    drawContext.canvas.nativeCanvas.apply {
//                        val paint = Paint().apply {
//                            textSize = 48.sp.toPx()
//                        }
//                        drawText("Текст", 354f, 354f, paint)
//                    }
//                }
//        )

//        ConstraintLayout(modifier = Modifier.wrapContentSize()) {
//            val (image, resourceText, soulText, talentText) = createRefs()
//            Image(
//                imageVector = ImageVector.vectorResource(id = R.drawable.matrix),
//                contentDescription = null,
//                modifier = Modifier
//                    .constrainAs(image) {}
//            )
//            Text(
//                text = resource.intValue.toString(),
//                modifier = Modifier
//                    .padding(start = 16.dp)
//                    .constrainAs(resourceText) {
//                    top.linkTo(image.top)
//                    start.linkTo(image.start)
//                    bottom.linkTo(image.bottom)
//                },
//                fontSize = 20.sp,
//                color = Color.White
//            )
//        }

//        val context = LocalContext.current
//        val bitmap = remember {
//            val originalBitmap = BitmapFactory.decodeResource(context.resources, R.raw.matrix)
//            val mutableBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true)
//
//            Canvas(mutableBitmap).apply {
//                drawText("Текст", 1200f, 1250f, Paint().apply {
//                    color = Color.Black.value.toInt()
//                    alpha = 255
//                    textSize = 100f
//                })
//            }
//
//            mutableBitmap
//        }
//        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "")

        DrawMatrix()

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
    val rawText =
        KarmaCode.context.resources.openRawResource(id).bufferedReader().use { it.readText() }
    rawText.lines().forEachIndexed { index, line ->
        if (line.toIntOrNull() == key) {
            return rawText.lines()[index + 1]
        }
    }
    return ""
}

@Composable
fun DrawMatrix() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
        ) {
            /*Start*/
            Row(
                modifier = Modifier
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp, 45.dp)
                        .background(Color.Magenta)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp, 45.dp)
                        .background(Color.Blue)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp, 35.dp)
                        .background(Color.Cyan)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }

            /*Centre*/
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp, 50.dp)
                        .background(Color.Yellow)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 25.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                        .size(45.dp, 45.dp)
                        .background(Color.White)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                        .size(35.dp, 35.dp)
                        .background(Color.White)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }

            /*End*/
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp, 35.dp)
                        .background(Color.Yellow)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                        .size(45.dp, 45.dp)
                        .background(Color.White)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp, 50.dp)
                        .background(Color.Red)
                ) {
                    Text(
                        text = "1",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Black,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}