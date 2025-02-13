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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.task.healthylife.R

@Composable
fun ChooseGenderScreen(navController: NavController){

    var selectedOption by remember { mutableStateOf("Option 1") }

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
                        navController.navigate("main") {
                            popUpTo("gender") { inclusive = true }
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
                    text = "1/5",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    color = Color(0xFF72777A)
                )
            }
        }

        Text(
            text = "What’s your gender?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = "We need to take your gender into account to\nbetter personalise your experience.",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF72777A),
            modifier = Modifier.padding(top = 30.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                ){
                    Image(
                        painter = painterResource(R.drawable.male),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Male",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                RadioButton(
                    selected = selectedOption == "Option 1",
                    onClick =  {selectedOption = "Option 1"},
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3BA170))

                )


            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                ){
                    Image(
                        painter = painterResource(R.drawable.female),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().padding(5.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = "Female",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                RadioButton(
                    selected = selectedOption == "Option 2",
                    onClick =  {selectedOption = "Option 2"},
                    colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3BA170))

                )
            }
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
                navController.navigate("age"){
                    popUpTo("gender") { inclusive = true }
                }
            }
        ) {
            Text(
                text = "Continue"
            )
        }
    }
}