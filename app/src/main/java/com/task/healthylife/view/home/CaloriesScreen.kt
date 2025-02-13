package com.task.healthylife.view.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.viewModel.StepCounterViewModel

@Composable
fun CaloriesScreen(
    viewModel: StepCounterViewModel = viewModel(),
    navController: NavController
) {

    val stepCount by viewModel.currentStepCount.collectAsState()
    val stepRecords by viewModel.stepRecords.collectAsState(emptyList())
    val calories = (stepCount.toFloat()*0.1).toInt()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color(0xFFFFD188).copy(alpha = 0.2f)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = CircleShape)
                    .clickable {
                        navController.navigate("main") {
                            popUpTo("calories") { inclusive = true }
                        }
                    },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    tint = Color(0xFF72777A)
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Calories",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF404446)
                )
            }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = CircleShape)
                    .clickable { },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    painter = painterResource(R.drawable.menu_meatballs),
                    contentDescription = null,
                    tint = Color(0xFF72777A)
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 30.dp)
                .size(230.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                drawCircle(
                    color = Color(0xFF9FF2CA),
                    style = Stroke(width = 20.dp.toPx())
                )
                drawArc(
                    color = Color(0xFF3BA170),
                    startAngle = -90f,
                    sweepAngle = ((calories.toFloat())/ 1000)* 360f,
                    useCenter = false,
                    style = Stroke(width = 23.dp.toPx(), cap = StrokeCap.Round),
                    size = Size(size.minDimension, size.minDimension)
                )
            }
            Canvas(
                modifier = Modifier
                    .padding(top = 2.5.dp)
                    .size(15.dp)
                    .align(Alignment.TopCenter)
            ) {
                drawCircle(
                    color = Color.White
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.calories),
                    contentDescription = null,
                    tint = Color(0xFF3BA170),
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "$calories",
                    color = Color(0xFF3BA170),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Calories",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF404446)
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFEBF4FF))
                    .clickable {

                    }
                    .padding(5.dp)
            ) {
                Text(
                    text = " Weekly",
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
                .weight(1f)
        ) {
            items(stepRecords.sortedByDescending { it.id }){step ->
                CaloriesRecordItem(
                    step = (step.stepCount.toFloat()*0.1).toInt(),
                    date = step.day,
                    max = 1000
                )
            }
        }
    }
}


@Composable
fun CaloriesRecordItem(
    step:Int,
    date:String,
    max:Int
){
    val progress = (step.toFloat() / max) * 1f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = date,
            fontSize = 11.sp,
            color = Color(0xFF72777A)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Canvas(
            modifier = Modifier
                .height(15.dp)
                .weight(1f)
        ) {
            drawLine(
                color = Color(0xFF9FF2CA),
                start = Offset(0f,size.height/2),
                end = Offset(size.width,size.height/2),
                strokeWidth = size.height,
                cap = StrokeCap.Round
            )
            drawLine(
                color = Color(0xFF3BA170),
                start = Offset(0f,size.height/2),
                end = Offset(size.width*progress,size.height/2),
                strokeWidth = size.height,
                cap = StrokeCap.Round
            )
            drawCircle(
                color = Color.White,
                radius = size.height/3,
                center = Offset(0f,size.height/2)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = step.toString(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF72777A)
        )
    }

}