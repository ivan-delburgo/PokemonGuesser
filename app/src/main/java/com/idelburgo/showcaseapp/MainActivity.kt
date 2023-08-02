package com.idelburgo.showcaseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.idelburgo.showcaseapp.navigation.ShowcaseNavhost
import com.idelburgo.showcaseapp.ui.theme.ShowcaseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowcaseAppTheme {
                val navController = rememberNavController()
                ShowcaseNavhost(
                    navController = navController
                )
            }
        }
    }
}





