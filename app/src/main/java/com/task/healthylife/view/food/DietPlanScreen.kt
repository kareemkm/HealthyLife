package com.task.healthylife.view.food

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.task.healthylife.R
import com.task.healthylife.classes.DietType
import com.task.healthylife.model.Day
import com.task.healthylife.model.Meal
import com.task.healthylife.viewModel.DietPlanViewModel

@Composable
fun DietPlanScreen(
    viewModel: DietPlanViewModel = viewModel(),
    navController: NavController,
    dietType: DietType
) {

    LaunchedEffect(dietType) {
        viewModel.getDietPlan(dietType)
    }

    val dietPlan by viewModel.dietPlan.collectAsState()

    var showItem by remember { mutableIntStateOf(1) }
    val scrollState = rememberScrollState()
    var previousScrollOffset by remember { mutableIntStateOf(0) }
    var isRowVisible by remember { mutableStateOf(true) }

    LaunchedEffect(scrollState.value) {
        isRowVisible = scrollState.value <= previousScrollOffset
        previousScrollOffset = scrollState.value

    }

    dietPlan?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.photo_back),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
            DietDayItem(
                day = dietPlan!!.days[showItem - 1],
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                navController = navController
            )
            AnimatedVisibility(
                visible = isRowVisible,
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 60.dp)
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF3BA170)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    it.days.forEach { day ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = if (showItem == day.day) 10.dp else 0.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    showItem = day.day
                                }
                        ) {
                            Text(
                                text = when (day.day) {
                                    1 -> "Sat"
                                    2 -> "Sun"
                                    3 -> "Mon"
                                    4 -> "Tue"
                                    5 -> "Wed"
                                    6 -> "Thu"
                                    7 -> "Fri"
                                    else -> ""
                                },
                                color = if (showItem == day.day) Color(0xFFFFD188) else Color.White,
                                fontSize = if (showItem == day.day) 22.sp else 14.sp,
                                fontWeight = if (showItem == day.day) FontWeight.Bold else FontWeight.W500

                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun DietDayItem(
    day: Day,
    modifier: Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
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
                            popUpTo("dietPlan/{dietType}") { inclusive = true }
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
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawCircle(
                        color = Color(0xFFFFD188),
                        style = Stroke(width = 10.dp.toPx())
                    )
                    drawArc(
                        color = Color(0xFF3BA170),
                        startAngle = -90f,
                        sweepAngle = (day.day_total_calories.toFloat() / 3000) * 360f,
                        useCenter = false,
                        style = Stroke(width = 10.dp.toPx())

                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${day.day_total_calories}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3BA170)
                    )
                    Text(
                        text = "Total Kcal",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFFFFD188)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .shadow(elevation = 20.dp, shape = RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)

        ) {
            day.meals.forEach { meal ->
                MealItem(
                    meal = meal,
                    navController = navController
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun MealItem(meal: Meal, navController: NavController) {

    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }
            ) {
                val image = Uri.encode(meal.items[0].image_url)
                navController.navigate("mealDetails/$image/${meal.name}/${meal.items[0].food}/${meal.items[0].calories}/${meal.items[0].recipe}/${meal.items[0].quantity}")
            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(150.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(
                    width = 1.dp,
                    color = if (meal.name == "snack") Color(0xFFFFD188) else Color(0xFF3BA170),
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box {
                Icon(
                    painter = painterResource(R.drawable.rectangle_9404),
                    contentDescription = null,
                    tint = if (meal.name == "snack") Color(0xFFFFD188) else Color(0xFF3BA170)
                )
                Text(
                    text = meal.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp, start = 10.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 13.dp)
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Icon(
                    painter = painterResource(R.drawable.cal),
                    contentDescription = null
                )
                Text(
                    text = "${meal.total_calories} kcal",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = meal.items[0].food,
                        fontWeight = FontWeight.Bold,

                        )
                }
            }

        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(width = 3.dp, color = Color.White, shape = CircleShape)
                .clip(CircleShape)
                .background(Color.White)
                .align(Alignment.TopCenter)
        ) {
            AsyncImage(
                model = meal.items[0].image_url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}