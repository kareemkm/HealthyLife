package com.task.healthylife.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.task.healthylife.R
import com.task.healthylife.viewModel.AuthViewModel

@Composable
fun ProfileScreen(authViewModel: AuthViewModel = viewModel(), navController: NavController) {

    val listUsername by authViewModel.users.collectAsState(emptyList())

    var isLogoutDialog by rememberSaveable { mutableStateOf(false) }
    LogoutDialog(
        isOpen = isLogoutDialog,
        onDismissClick = {isLogoutDialog = false},
        onConfirmClick = {
            authViewModel.logout()
            navController.navigate("onboarding"){
                popUpTo("main"){inclusive = true}
            }
            isLogoutDialog = false
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .fillMaxWidth()
                .height(90.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp) 
                    .weight(1f)
            ) {
                Text(
                    text = "Hello !",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3BA170)
                )
                listUsername.maxByOrNull { it.id }.let {
                    if (it != null) {
                        Text(
                            text = it.username,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                )
            }

        }

        Box(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 30.dp)
                .fillMaxWidth()
                .height(500.dp)
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                ProfileItems(
                    image = R.drawable.profile2,
                    label = "My Account",
                    onClick = {}
                )
                ProfileItems(
                    image = R.drawable.notification2,
                    label = "Help & Support",
                    onClick = {}
                )
                ProfileItems(
                    image = R.drawable.heart,
                    label = "About App",
                    onClick = {}
                )
                ProfileItems(
                    image = R.drawable.settings,
                    label = "Settings",
                    onClick = {}
                )
                ProfileItems(
                    image = R.drawable.logout,
                    label = "Log out",
                    onClick = {
                        isLogoutDialog = true
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileItems(
    image: Int,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 15.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 15.dp)
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(Color(0xFF3BA170).copy(0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f)
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFFABABAB),
            modifier = Modifier.padding(end = 15.dp)
        )

    }
}

@Composable
fun LogoutDialog(
    isOpen:Boolean,
    onDismissClick:() -> Unit,
    onConfirmClick:() -> Unit
){
    if (isOpen){
        AlertDialog(
            onDismissRequest = {onDismissClick()},
            shape = RoundedCornerShape(20.dp),
            containerColor = Color.White,
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .size(70.dp)
                            .clip(shape = CircleShape)
                            .background(Color(0xFF3BA170).copy(0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        )
                    }
                    Text(
                        text = "Are you sure to\nLogout",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        color = Color(0xFF3BA170),
                        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
                    )
                }
            },
            dismissButton = {
                TextButton (onClick = onDismissClick) {
                    Text(
                        text = "Cancel",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            confirmButton = {
                TextButton (onClick = onConfirmClick) {
                    Text(
                        text = "Logout",
                        color = Color(0xFF3BA170),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }

        )
    }
}















