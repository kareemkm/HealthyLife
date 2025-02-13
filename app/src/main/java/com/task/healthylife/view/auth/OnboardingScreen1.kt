package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun OnboardingScreen1(){
    Column (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Image(
            painter = painterResource(R.drawable.undraw_cooking_p7m1),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        )
        Text(
            text = "The program allows you to design a personalized diet plan that helps you achieve your health goals easily and effectively!",
            fontSize = 18.sp,
            color = Color(0xFF3BA170),
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            modifier = Modifier
                .padding(top = 100.dp)
                .padding(horizontal = 40.dp)
        )
    }

}