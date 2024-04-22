package edu.towson.outfitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.*
import edu.towson.outfitapp.data.User
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.login.LoginScreen
import edu.towson.outfitapp.profile.AccountSettingsScreen
import edu.towson.outfitapp.signup.SignUpScreen
import edu.towson.outfitapp.ui.theme.OutfitAppTheme
import edu.towson.outfitapp.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutfitAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController,userViewModel)
                    }
                    composable("signUp") {
                        SignUpScreen(navController,userViewModel)
                    }
                    composable("userProfile") {
                        UserProfileScreen(navController,userViewModel)
                    }
                    composable("accountSettings"){
                        AccountSettingsScreen(navController,userViewModel)
                    }
                    composable("userFeed"){

                    }
                }
            }
        }
    }
}


