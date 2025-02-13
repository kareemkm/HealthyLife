package com.task.healthylife.view.food

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.classes.DietType

@Composable
fun ChooseGoalScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 40.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = CircleShape)
                        .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = CircleShape)
                        .clickable {
                            navController.navigate("height") {
                                popUpTo("goal") { inclusive = true }
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
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE3E5E5),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = "5/5",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                        color = Color(0xFF72777A)
                    )
                }
            }

            Text(
                text = "What’s your main goal?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "Knowing your goal helps us create your\npersonalised plan.",
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF72777A),
                modifier = Modifier.padding(top = 30.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            TypeCard(
                image = R.drawable.damble,
                label = "Inflating",
                content = "Increase physical fitness",
                onClick = {
                    navController.navigate("dietPlan/${DietType.WEIGHT_GAIN}"){
                        popUpTo("goal"){inclusive = true}
                    }
                }
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TypeCard(
                    image = R.drawable.yoga,
                    label = "Stabilization",
                    content = "Increase physical fitness",
                    onClick = {
                        navController.navigate("dietPlan/${DietType.WEIGHT_MAINTENANCE}"){
                            popUpTo("goal"){inclusive = true}
                        }
                    }
                )
                TypeCard(
                    image = R.drawable.diet_schedule,
                    label = "diet",
                    content = "Increase physical fitness",
                    onClick = {
                        navController.navigate("dietPlan/${DietType.WEIGHT_LOSS}"){
                            popUpTo("goal"){inclusive = true}
                        }
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFF3BA170).copy(alpha = 0.2f)
                        )
                    )
                )
        )
    }
}

@Composable
fun TypeCard(
    image: Int,
    label: String,
    content: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .size(150.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF9FAFB))
            .clickable {
                onClick()
            }

    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp)
                .size(30.dp)
        )
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp, start = 15.dp)
        )
        Text(
            text = content,
            fontSize = 12.sp,
            color = Color(0xFF72777A),
            lineHeight = 16.sp,
            modifier = Modifier.padding(top = 5.dp, start = 15.dp)

        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}











