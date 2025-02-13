package com.task.healthylife.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.task.healthylife.classes.DietType
import com.task.healthylife.firebase.FirebaseAuth
import com.task.healthylife.view.home.CaloriesScreen
import com.task.healthylife.view.chatbot.ChatbotScreen
import com.task.healthylife.view.home.DrListScreen
import com.task.healthylife.view.auth.LoginScreen
import com.task.healthylife.view.auth.SigninScreen
import com.task.healthylife.view.auth.SplashScreen
import com.task.healthylife.view.food.ChooseGenderScreen
import com.task.healthylife.view.food.ChooseGoalScreen
import com.task.healthylife.view.food.DietPlanScreen
import com.task.healthylife.view.food.InputAge
import com.task.healthylife.view.food.InputHeightScreen
import com.task.healthylife.view.food.InputWeightScreen
import com.task.healthylife.view.food.MealDetailsScreen
import com.task.healthylife.view.home.StepCountScreen
import kotlinx.coroutines.delay

@Composable
fun App() {
    var isSplash by remember { mutableStateOf(true) }
    val navController = rememberNavController()

    LaunchedEffect(key1 = 1) {
        delay(1000*2)
        isSplash = false
    }

    NavHost(
        navController = navController,
        startDestination = if (isSplash) "splash" else if (FirebaseAuth.getCurrentUser() != null) "main" else "onboarding"
    ) {
        composable("splash") { SplashScreen() }
        composable("onboarding") { OnboardingScreen(navController = navController) }
        composable("signin") { SigninScreen(navController = navController) }
        composable("Login") { LoginScreen(navController = navController) }
        composable("main") { MainScreen(navController = navController) }
        composable("chatbot") { ChatbotScreen(navController = navController) }
        composable("drList") { DrListScreen(navController = navController) }
        composable("step") { StepCountScreen(navController = navController) }
        composable("calories") { CaloriesScreen(navController = navController) }
        composable("gender") { ChooseGenderScreen(navController = navController) }
        composable("age") { InputAge(navController = navController) }
        composable("weight") { InputWeightScreen(navController = navController) }
        composable("height") { InputHeightScreen(navController = navController) }
        composable("goal") { ChooseGoalScreen(navController = navController) }
        composable(
            route = "dietPlan/{dietType}",
            arguments = listOf(navArgument("dietType") { type = NavType.StringType })
        ) { backStackEntry ->
            val dietTypeString = backStackEntry.arguments?.getString("dietType") ?: "WEIGHT_LOSS"
            val dietType = DietType.valueOf(dietTypeString)
            DietPlanScreen(navController = navController, dietType = dietType)
        }
        composable("mealDetails/{image}/{meal.name}/{meal.items[0].food}/{meal.items[0].calories}/{meal.items[0].recipe}/{meal.items[0].quantity}") { backStackEntry ->
            val image =backStackEntry.arguments?.getString("image")
            val name = backStackEntry.arguments?.getString("meal.name")
            val food = backStackEntry.arguments?.getString("meal.items[0].food")
            val calories = backStackEntry.arguments?.getString("meal.items[0].calories")
            val recipe = backStackEntry.arguments?.getString("meal.items[0].recipe")
            val quantity = backStackEntry.arguments?.getString("meal.items[0].quantity")

            MealDetailsScreen(
                navController = navController,
                image = image,
                name = name,
                food = food,
                calories = calories,
                recipe = recipe,
                quantity = quantity
            )

        }
    }
}