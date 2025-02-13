package com.task.healthylife.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.task.healthylife.R
import com.task.healthylife.view.food.FoodScreen
import com.task.healthylife.view.home.HomeScreen
import com.task.healthylife.view.profile.ProfileScreen
import com.task.healthylife.view.target.TargetScreen

@Composable
fun MainScreen(navController: NavController) {


    val scaffoldState = rememberScaffoldState()

    val bottomNavigationNavController = rememberNavController()
    Scaffold(
        backgroundColor = Color(0xFFF5F5F5),
        scaffoldState = scaffoldState,
        bottomBar = { MainBottomNavigation(navController = bottomNavigationNavController) },
        floatingActionButton = { FloatingActionButton(navController = navController) },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValue ->
        NavHost(
            navController = bottomNavigationNavController,
            startDestination = "home",
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
        ) {
            composable("home") { HomeScreen(navController = navController) }
            composable("target") { TargetScreen() }
            composable("food") { FoodScreen(navController = navController) }
            composable("profile") { ProfileScreen(navController = navController) }
        }
    }
}

@Composable
fun MainBottomNavigation(navController: NavController) {

    val currentRout = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(

        backgroundColor = Color(0xFF3BA170),
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(50.dp)
        )
    ) {
        BottomNavigation(
            modifier = Modifier
                .fillMaxSize(),
            backgroundColor = Color(0xFF3BA170),
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "",
                        tint = if (currentRout == "home") Color(0xFFFFD188) else Color.White,
                        modifier = Modifier.size(40.dp).padding(5.dp)
                    )

                },
                label = {
                    Text(
                        text = if (currentRout == "home") "Home" else "",
                        color = Color(0xFFFFD188),
                        fontSize = 11.sp
                    )
                },
                selected = currentRout == "home",
                onClick = {
                    if (currentRout != "home") {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.target),
                        contentDescription = "",
                        tint = if (currentRout == "target") Color(0xFFFFD188) else Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 5.dp)

                    )
                },
                label = {
                    Text(
                        text = if (currentRout == "target") "Target" else "",
                        color = Color(0xFFFFD188),
                        fontSize = 11.sp
                    )
                },
                selected = currentRout == "target",
                onClick = {
                    if (currentRout != "target") {
                        navController.navigate("target") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.food),
                        contentDescription = "",
                        tint = if (currentRout == "food") Color(0xFFFFD188) else Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp)


                    )
                },
                label = {
                    Text(
                        text = if (currentRout == "food") "Food" else "",
                        color = Color(0xFFFFD188),
                        fontSize = 11.sp

                    )
                },
                selected = currentRout == "food",
                onClick = {
                    if (currentRout != "food") {
                        navController.navigate("food") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "",
                        tint = if (currentRout == "profile") Color(0xFFFFD188) else Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp)

                    )
                },
                label = {
                    Text(
                        text = if (currentRout == "profile") "Profile" else "",
                        color = Color(0xFFFFD188),
                        fontSize = 11.sp

                    )
                },
                selected = currentRout == "profile",
                onClick = {
                    if (currentRout != "profile") {
                        navController.navigate("profile") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )


        }
    }
}

@Composable
fun FloatingActionButton(navController: NavController) {

    FloatingActionButton(
        onClick = {
            navController.navigate("chatbot") {
                popUpTo("chatbot") { inclusive = true }
            }
        },
        shape = CircleShape,
        containerColor = Color(0xFFFFD188),
        modifier = Modifier
            .size(70.dp)
            .padding(1.dp)

    ) {
        Icon(
            painter = painterResource(R.drawable.chatbot),
            contentDescription = null,
            tint = Color(0xFF3BA170)
        )
    }

}



















