package edu.towson.outfitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.*
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.signup.SignUpPage
import androidx.compose.ui.tooling.preview.Preview
import edu.towson.outfitapp.profile.UserProfileScreen
import edu.towson.outfitapp.ui.theme.LoginScreen
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
                        SignUpPage(navController)
                    }
                    composable("userProfile") {
                        UserProfileScreen()
                    }
                }
            }
        }
    }
}


