package edu.towson.outfitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.*
import edu.towson.outfitapp.DatabaseData.PagesForTesting.ImageUploadLogic
import edu.towson.outfitapp.DatabaseData.PagesForTesting.signUpPage
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.login.LoginScreen
import edu.towson.outfitapp.profile.AccountSettingsScreen
import edu.towson.outfitapp.signup.SignUpScreen
import edu.towson.outfitapp.ui.theme.OutfitAppTheme
import edu.towson.outfitapp.viewmodel.UserViewModelF
import edu.towson.outfitapp.MainPage.ForYouPage

class MainActivity : ComponentActivity() {
    private val userViewModelF: UserViewModelF by viewModels() // HEY SORRY BUT I CHANGED THE NAME OF NON DATABASE USERVIEWMODEL  SINCE THE NAMES CONFLICTED
    private val userViewModel: UserViewModel by viewModels()
    private val postViewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutfitAppTheme {
                signUpPage(userViewModel) // FOR TESTING YOU CAN DELETE
                //ImageUploadLogic(postViewModel) // HEY SORRY AGAIN I HAD TO PUT THIS HERE FOR TESTING YOU CAN DELETE IT IF YOU WANT
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "login") {
//                    composable("login") {
//                        LoginScreen(navController,userViewModelF)
//                    }
//                    composable("signUp") {
//                        SignUpScreen(navController,userViewModelF)
//                    }
//                    composable("userProfile") {
//                        UserProfileScreen(navController,userViewModelF)
//                    }
//                    composable("accountSettings"){
//                        AccountSettingsScreen(navController,userViewModelF)
//                    }
//                    composable("userFeed"){
//                        ForYouPage(navController,userViewModelF)
//                    }
//                }
            }
        }
    }
}


