package com.task.healthylife.view.food

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.task.healthylife.R
import kotlin.math.abs
import kotlin.math.max

@Composable
fun InputWeightScreen(navController: NavController){
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
                        navController.navigate("age") {
                            popUpTo("weight") { inclusive = true }
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
                    .border(width = 1.dp, color = Color(0xFFE3E5E5), shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "3/5",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    color = Color(0xFF72777A)
                )
            }
        }
        Text(
            text = "What’s your weight?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = "We would like to create a personalised plan\nwith your weight in mind.",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF72777A),
            modifier = Modifier.padding(top = 30.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Box(
                modifier = Modifier
                    .padding(top = 95.dp)
                    .padding(horizontal = 20.dp)
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF6C7072).copy(alpha = 0.1f))
            ){
                Text(
                    text = "kg",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items((40..180).toList()){ weight ->
                    val centerOffset = listState.layoutInfo.viewportEndOffset/3
                    val itemOffset = listState.layoutInfo.visibleItemsInfo
                        .find { it.index == (weight - 40) }
                        ?.offset ?: 0
                    val distanceFromCenter = abs(itemOffset - centerOffset)
                    val scale = max(1f, 1.5f - (distanceFromCenter / 300f))
                    val animatedState by animateFloatAsState(scale)
                    Text(
                        text = "$weight",
                        fontSize = (24*animatedState).sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier
                            .padding(vertical = 7.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White,
                                Color.Transparent,
                                Color.Transparent,
                                Color.White,
                                Color.White
                            )
                        )
                    )
            )
        }
        Button(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(bottom = 70.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3BA170),
                contentColor = Color.White
            ),
            onClick = {
                navController.navigate("height"){
                    popUpTo("weight") { inclusive = true }
                }
            }
        ) {
            Text(
                text = "Continue"
            )
        }
    }
}