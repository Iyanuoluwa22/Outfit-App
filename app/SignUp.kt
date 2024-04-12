package edu.towson.outfitapp

import edu.towson.outfitapp.ui.theme.OutfitAppTheme
import edu.towson.outfitapp.profile.UserProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutfitAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initializes NavController
                    val navController = rememberNavController()
                    // Sets up NavHost
                    NavHost(navController = navController, startDestination = "signup") {
                        // Defines the composable for SignUpPage
                        composable("signup") {
                            SignUpPage(navController)
                        }
                        // Defines the composable for UserProfileScreen
                        composable("userProfile") {
                            UserProfileScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SignUpPage(navController: NavHostController) {
    var fname by remember { mutableStateOf("") }
    var lname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Surface(
        color = Color.LightGray,
    ) {
        Column(
            modifier = Modifier.padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome!")
            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "")

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "First Name:", modifier = Modifier.align(Alignment.Start))
            TextField(value = fname, onValueChange = { fname = it })

            Text(text = "Last Name:", modifier = Modifier.align(Alignment.Start))
            TextField(value = lname, onValueChange = { lname = it })

            Text(text = "Username:", modifier = Modifier.align(Alignment.Start))
            TextField(value = username, onValueChange = { username = it })

            Text(text = "Password:", modifier = Modifier.align(Alignment.Start))
            TextField(value = pass, onValueChange = { pass = it })

            OutlinedButton(
                onClick = {
                    navController.navigate("userProfile")
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Cyan)
            ) {
                Text(text = "Sign Up!")
            }
        }
    }
}


@Preview
@Composable
fun PreviewSignUp() {
    SignUpPage(navController = rememberNavController())
}
