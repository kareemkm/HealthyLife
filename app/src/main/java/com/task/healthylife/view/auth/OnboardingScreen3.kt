package com.task.healthylife.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.task.healthylife.R

@Composable
fun OnboardingScreen3(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ellipse_8),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.undraw_shared_goals_re_jvqd_1),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(20.dp)
                        .padding(top = 40.dp)
                )
                Text(
                    text = "Start your experience now",
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 50.dp)
                )
                Text(
                    text = "Log in or create a new account!",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 20.dp)

                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 50.dp)
        ) {
            Button(
                shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3BA170),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp),
                onClick = {
                    navController.navigate("login"){
                        popUpTo("onboarding"){inclusive = true}
                    }
                }
            ) {
                Text("login")
            }
            Button(
                shape = RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF3BA170)
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF3BA170),
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    ),
                onClick = {
                    navController.navigate("signin"){
                        popUpTo("onboarding"){inclusive = true}
                    }
                }
            ) {
                Text("signin")
            }
        }

    }
}

