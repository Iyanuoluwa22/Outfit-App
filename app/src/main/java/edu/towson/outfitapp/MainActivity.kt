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
                // Move Surface inside the composable function
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //UserProfileScreen()
                    LoginScreen()
                }
            }
        }
    }
}


@Composable
fun LoginScreen(navController: NavController) {
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Outfitify",
                Modifier.size(300.dp),
                color = Color.Blue
            )

            // Create EditText for username
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                label = { Text("Username") }
            )

            // Create EditText for password
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            // Create Button for login
            Button(
                onClick = {
                          navController.navigate("userProfile")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }

            // Create Button to navigate to SignUp page
            Button(
                onClick = {
                    navController.navigate("signUp")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}


