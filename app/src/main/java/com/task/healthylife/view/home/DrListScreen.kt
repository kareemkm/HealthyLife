package com.task.healthylife.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.viewModel.DrListViewModel

@Composable
fun DrListScreen(
    navController: NavController,
    drListViewModel: DrListViewModel = viewModel()
) {

    val drList by drListViewModel.drList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFD188).copy(alpha = 0.2f),
                        Color(0xFF3BA170).copy(alpha = 0.2f)
                    )
                )
            )
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
                            popUpTo("drList") { inclusive = true }
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
                    text = "Dr.List",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (drList.isEmpty()) {
                item {
                    Box{
                        Image(
                            painter = painterResource(R.drawable.no_data),
                            contentDescription = null,
                            modifier = Modifier
                                .size(250.dp)
                        )
                    }
                }
            } else {
                items(drList) { dr ->
                    DrListItem(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .width(350.dp)
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