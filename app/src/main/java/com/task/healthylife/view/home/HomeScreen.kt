package com.task.healthylife.view.home


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.task.healthylife.R
import com.task.healthylife.viewModel.DrListViewModel
import com.task.healthylife.viewModel.StepCounterViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun HomeScreen(
    stepCounterViewModel: StepCounterViewModel = viewModel(),
    drListViewModel: DrListViewModel = viewModel(),
    navController: NavController
) {
    val stepCount by stepCounterViewModel.currentStepCount.collectAsState()
    val calories = (stepCount.toFloat()*0.1).toInt()
    val drList by drListViewModel.drList.collectAsState()

    var isrefrech by remember { mutableStateOf(false) }


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isrefrech),
        onRefresh = {
            GlobalScope.launch {
                isrefrech = true
                drListViewModel.getDrList()
                delay(2000)
                isrefrech = false
            }
        },
        indicator = { state, refreshTriggerDistance ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTriggerDistance,
                contentColor = Color(0xFF3BA170)

            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3BA170))
            ){
                Text(
                    text = "Statistics",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF5F5F5),
                    modifier = Modifier
                        .padding(top = 40.dp, start = 20.dp, bottom = 20.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    tint = Color(0xFF3BA170)
                )
                Text(
                    text = getCurrentDate(),
                    fontSize = 19.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black.copy(alpha = 0.7f),
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Text(
                text = "Overview",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF404446),
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp, bottom = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                OverviewItems(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.sleep,
                    value = "8.30 h",
                    type = "Sleep hours"
                )
                OverviewItems(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.time_circle_6,
                    value = "3h 14m",
                    type = "Total Time"
                )
                OverviewItems(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.damble,
                    value = "15",
                    type = "Exercises"
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    Text(
                        text = "Daily progress",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF404446),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                navController.navigate("calories")
                            }
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(shape = CircleShape)
                                .background(Color(0xFF3BA170))
                        )
                        Text(
                            text = "Calories",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(start = 30.dp)
                    ) {
                        Text(
                            text = "${(stepCount.toFloat()*0.1).toInt()}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3BA170)
                        )
                        Text(
                            text = "/1000",
                            fontSize = 16.sp,
                            color = Color(0xFF72777A)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                navController.navigate("step")
                            }
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(shape = CircleShape)
                                .background(Color(0xFFFFD188))
                        )
                        Text(
                            text = "Steps",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(start = 30.dp)
                    ) {
                        Text(
                            text = "$stepCount",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD188)
                        )
                        Text(
                            text = "/5000",
                            fontSize = 16.sp,
                            color = Color(0xFF72777A)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(
                            modifier = Modifier.size(150.dp)
                        ) {
                            drawCircle(
                                color = Color(0xFF9FF2CA),
                                style = Stroke(width = 15.dp.toPx())
                            )
                            drawArc(
                                color = Color(0xFF3BA170),
                                startAngle = -90f,
                                sweepAngle = (calories.toFloat()/1000)*360f,
                                useCenter = false,
                                style = Stroke(width = 17.dp.toPx(), cap = StrokeCap.Round),
                                size = Size(size.minDimension, size.minDimension)
                            )
                        }
                        Canvas(
                            modifier = Modifier.size(90.dp)
                        ) {
                            drawCircle(
                                color = Color(0xFFFFEFD7),
                                style = Stroke(width = 10.dp.toPx())
                            )
                            drawArc(
                                color = Color(0xFFFFD188),
                                startAngle = -90f,
                                sweepAngle = (stepCount.toFloat()/5000)*360f,
                                useCenter = false,
                                style = Stroke(width = 13.dp.toPx(), cap = StrokeCap.Round),
                                size = Size(size.minDimension, size.minDimension)
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .padding(20.25.dp)
                                .size(10.dp)
                                .align(Alignment.TopCenter)
                        ) {
                            drawCircle(
                                color = Color.White
                            )
                        }
                        Canvas(
                            modifier = Modifier
                                .padding(51.dp)
                                .size(8.dp)
                                .align(Alignment.TopCenter)
                        ) {
                            drawCircle(
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dr.List",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF404446),
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("drList")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "  See more",
                        fontSize = 11.sp,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .height(230.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (drList.isEmpty()){
                    item {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.no_data),
                                contentDescription = null
                            )
                        }
                    }
                }else{
                    items(drList.take(5)) { dr ->
                        DrListItem(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(290.dp)
                                .height(180.dp),
                            backgroundHex = dr.backgroundColor,
                            doctorName = dr.doctorName,
                            date = dr.date,
                            advice = dr.advice,
                            location = dr.location
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OverviewItems(
    modifier: Modifier,
    icon: Int,
    value: String,
    type: String
) {
    Column(
        modifier = modifier
            .height(125.dp)
            .width(100.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 15.dp, top = 15.dp)
                .size(32.dp)
                .clip(shape = CircleShape)
                .background(Color(0xFFF2F8FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color(0xFF3BA170)
            )
        }
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
        )
        Text(
            text = type,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF72777A),
            modifier = Modifier
                .padding(start = 15.dp)
        )

    }
}

@Composable
fun DrListItem(
    modifier: Modifier,
    backgroundHex: String,
    doctorName: String,
    date: String,
    advice: String,
    location: String
) {
    val backgroundColor = Color(android.graphics.Color.parseColor(backgroundHex))
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp))
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color(0xFF3BA170)
                    )
                ),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)
            )
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = doctorName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 16.sp
                )
                Text(
                    text = date,
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    color = Color(0xFF404446)
                )
            }
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = advice,
                fontSize = 14.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color(0xFF6C7072),
                    modifier = Modifier
                        .size(20.dp)

                )
                Text(
                    text = location,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6C7072)
                )
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color(0xFFEA4335),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}



fun getCurrentDate() : String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("ddMMM  yyyy")
    return currentDate.format(formatter)
}












