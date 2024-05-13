package edu.towson.outfitapp

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.*
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import edu.towson.outfitapp.DatabaseData.PagesForTesting.ImageUploadLogic
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.MainPage.ForYouPage
import edu.towson.outfitapp.data.AddData
import edu.towson.outfitapp.data.populateData
import edu.towson.outfitapp.login.LoginScreen
import edu.towson.outfitapp.profile.AccountSettingsScreen
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.profile.UserSearch
import edu.towson.outfitapp.profile.ViewAUser
import edu.towson.outfitapp.signup.SignUpPage
import edu.towson.outfitapp.ui.theme.OutfitAppTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val postViewModel: PostViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme
            ){
                val populate = userViewModel.getAllUsers().value?.size
                if (populate == 0){
                    populateData(userViewModel, postViewModel)
                }
                OutfitAppTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {//simple Navigation menu
                        composable("login") {
                            LoginScreen(navController, userViewModel,postViewModel)
                        }
                        composable("signUp") {
                            SignUpPage(navController, userViewModel = userViewModel, lifecycleScope,postViewModel)
                        }
                        composable("userProfile") {
                            UserProfileScreen(navController, userViewModel, postViewModel)
                        }
                        composable("accountSettings") {
                            AccountSettingsScreen(navController, userViewModel, lifecycleScope)
                        }
                        composable("userFeed") {
                            ForYouPage(navController, userViewModel, postViewModel)
                        }
                        composable("imageUpload") {
                            ImageUploadLogic(navController, postViewModel, userViewModel)
                        }
                        composable("userSearch") {
                            UserSearch(navController,userViewModel)
                        }
                        composable("viewUser"){
                            ViewAUser(navController,userViewModel,postViewModel)
                        }
                    }
                }
            }
        }
    }
}




