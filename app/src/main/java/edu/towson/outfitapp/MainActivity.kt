package edu.towson.outfitapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.PagesForTesting.ImageUploadLogic
import edu.towson.outfitapp.signup.signUpPage
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
//import edu.towson.outfitapp.MainPage.ForYouPage
import edu.towson.outfitapp.login.LoginScreen
import edu.towson.outfitapp.profile.AccountSettingsScreen
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.ui.theme.OutfitAppTheme
import androidx.lifecycle.lifecycleScope


class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val postViewModel: PostViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
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
                        signUpPage(navController, userViewModel = userViewModel, lifecycleScope)
                    }
                    composable("userProfile") {
                        UserProfileScreen(navController,userViewModel)
                    }
                    composable("accountSettings"){
                        AccountSettingsScreen(navController,userViewModel,lifecycleScope)
                    }
                    composable("userFeed"){
                        //ForYouPage(navController,userViewModel) can uncomment for testing, might crash though
                    }
                    composable("imageUpload"){
                        ImageUploadLogic(postViewModel, null)
                    }
                }
            }
        }
    }
}




