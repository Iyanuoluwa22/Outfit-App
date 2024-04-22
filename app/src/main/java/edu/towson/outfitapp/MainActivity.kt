package edu.towson.outfitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.login.LoginScreen
import edu.towson.outfitapp.profile.AccountSettingsScreen
import edu.towson.outfitapp.signup.SignUpScreen
import edu.towson.outfitapp.ui.theme.OutfitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutfitAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("signUp") {
                        SignUpScreen(navController)
                    }
                    composable("userProfile") {
                        UserProfileScreen(navController)
                    }
                    composable("accountSettings"){
                        AccountSettingsScreen(navController)
                    }
                    composable("userFeed"){

                    }
                }
            }
        }
    }
}


