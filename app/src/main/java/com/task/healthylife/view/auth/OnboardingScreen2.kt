@file:JvmName("OnboardingScreen2Kt")

package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.healthylife.R

@Composable
fun OnboardingScreen2(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding3),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 130.dp, start = 30.dp, end = 30.dp)
                .align(Alignment.TopCenter)
        )

        Text(
            text = "Start a chat with the bot to receive health tips and useful information.",
            color = Color(0xFF3BA170),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            lineHeight = 40.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 300.dp)
                .padding(horizontal = 30.dp)
        )

    }
}