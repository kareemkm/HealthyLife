package com.task.healthylife.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.task.healthylife.view.auth.OnboardingScreen1
import com.task.healthylife.view.auth.OnboardingScreen2
import com.task.healthylife.view.auth.OnboardingScreen3


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController){
    val pagerState = rememberPagerState()
    Box (
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            count = 3,
            modifier = Modifier
                .fillMaxSize()
        ) {page ->
            when(page){
                0 -> OnboardingScreen1()
                1 -> OnboardingScreen2()
                2 -> OnboardingScreen3(navController = navController)
            }
        }
        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
        )
    }
}

@Composable
fun DotsIndicator(totalDots: Int , selectedIndex:Int, modifier: Modifier){

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until totalDots){
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .height(10.dp)
                    .width(if (i == selectedIndex) 25.dp else 10.dp)
                    .background(
                        color = if (i == selectedIndex) Color(0xFF3BA170) else Color.LightGray,
                        shape = CircleShape
                    )
            )
    }
    }

}