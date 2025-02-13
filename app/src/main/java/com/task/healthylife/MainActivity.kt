package com.task.healthylife

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.task.healthylife.ui.theme.HealthyLifeTheme
import com.task.healthylife.view.App
import android.content.res.Configuration
import android.graphics.Color
import androidx.core.view.WindowCompat
import com.jakewharton.threetenabp.AndroidThreeTen
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // date init
        AndroidThreeTen.init(this)

        // language
        setAppLocal(this)

        //manage screen
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT


        // view
        setContent {
            HealthyLifeTheme {
                App()
            }
        }
    }


    private fun setAppLocal(context: Context){
        val locale = Locale("en")
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        resources.updateConfiguration(config,resources.displayMetrics)
    }
}