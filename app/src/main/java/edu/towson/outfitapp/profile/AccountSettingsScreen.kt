package edu.towson.outfitapp.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.data.User
import edu.towson.outfitapp.data.changeUsername
import edu.towson.outfitapp.data.isValidPassword
import edu.towson.outfitapp.data.isValidUsername
import edu.towson.outfitapp.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountSettingsScreen(navController: NavController, userViewModel: UserViewModel) {
    val mainUser by userViewModel.mainUser.collectAsState()
    var newUsername by remember { mutableStateOf(mainUser?.username) }
    var newPassword by remember { mutableStateOf(mainUser?.password) }
    var newFirstName by remember { mutableStateOf(mainUser?.firstName) }
    var newLastName by remember { mutableStateOf(mainUser?.lastName) }
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            newUsername?.let {
                TextField(
                    value = it,
                    onValueChange = { newUsername = it },
                    label = { Text("New Username") },
                    modifier = Modifier.padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
                )
            }
            newPassword?.let {
                TextField(
                    value = it,
                    onValueChange = { newPassword = it },
                    label = { Text("New Password") },
                    modifier = Modifier.padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
                )
            }
            newFirstName?.let {
                TextField(
                    value = it,
                    onValueChange = { newFirstName = it },
                    label = { Text("New First Name") },
                    modifier = Modifier.padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
                )
            }
            newLastName?.let {
                TextField(
                    value = it,
                    onValueChange = { newLastName = it },
                    label = { Text("New Last Name") },
                    modifier = Modifier.padding(10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
                )
            }
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    onClick = {
                        val validUsername = newUsername?.let { isValidUsername(it) }
                        val validPassword = newPassword?.let { isValidPassword(it) }
                        if (!validUsername!! || !validPassword!!) {
                            showErrorPopup = true // Show popup if username or password is invalid
                        } else {
                            newUsername?.let { mainUser?.username?.let { it1 -> changeUsername(it1, it) } } // change to (oldUsername, newUsername)
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
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Log Out")
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
    val dummyUser = User("test", "test123", "John", "Doe", "john.doe@example.com")
    val dummyUserViewModel = UserViewModel().apply {
        setUser(dummyUser)
    }
    AccountSettingsScreen(navController = rememberNavController(), dummyUserViewModel)
}
