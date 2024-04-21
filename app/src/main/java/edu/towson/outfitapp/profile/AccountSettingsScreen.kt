package edu.towson.outfitapp.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountSettingsScreen(navController: NavController) {
    var newUsername by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newFirstName by remember { mutableStateOf("") }
    var newLastName by remember { mutableStateOf("") }
    var showErrorPopup by remember { mutableStateOf(false) } // State for showing error popup

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.heightIn(10.dp, 200.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Cyan,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Outfitify",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp),
                        color = Color.White,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            TextField(
                value = newUsername,
                onValueChange = { newUsername = it },
                label = { Text("New Username") },
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = newFirstName,
                onValueChange = { newFirstName = it },
                label = { Text("New First Name") },
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = newLastName,
                onValueChange = { newLastName = it },
                label = { Text("New Last Name") },
                modifier = Modifier.padding(10.dp)
            )
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    onClick = {
                        val validUsername = isValidUsername(newUsername)
                        val validPassword = isValidPassword(newPassword)
                        if (!validUsername || !validPassword) {
                            showErrorPopup = true // Show popup if username or password is invalid
                        } else {
                            changeUsername(newUsername)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save Changes")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        // Navigate back logic
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }

    // Popup message for invalid input
    if (showErrorPopup) {
        AlertDialog(
            onDismissRequest = { showErrorPopup = false },
            title = {
                Text(text = "Invalid Input")
            },
            text = {
                Text(text = "Please enter a valid username and password.")
            },
            confirmButton = {
                Button(onClick = { showErrorPopup = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewAccountSettingsScreen() {
    AccountSettingsScreen(navController = rememberNavController())
}
