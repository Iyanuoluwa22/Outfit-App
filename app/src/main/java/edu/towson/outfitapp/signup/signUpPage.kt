package edu.towson.outfitapp.signup

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.PagesForTesting.MyDialog
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.data.isValidEmail
import edu.towson.outfitapp.data.isValidPassword
import edu.towson.outfitapp.data.isValidUsername
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun signUpPage(navController: NavController, userViewModel: UserViewModel, viewModelScope: CoroutineScope) {
    val users by userViewModel.getAllUsers().observeAsState(initial = emptyList())

    var userName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var accountExistDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User name") },
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )
        TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("Email", fontSize = 15.sp) },
            modifier = Modifier.padding(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", fontSize = 15.sp) },
            modifier = Modifier.padding(10.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )

        Row(modifier = Modifier.padding(20.dp)){
            Button(onClick = { navController.popBackStack()},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue))
            {
                Text(text = "<")
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    // Ensure all fields are filled
                    if (firstName.isNotEmpty() && lastName.isNotEmpty() && userEmail.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty()) {
                        // Check for valid email, username, and password
                        if (isValidEmail(userEmail) && isValidUsername(userName) && isValidPassword(password)) {

                            if(userViewModel.userDao.getUserByUsername(userName).value == null){
                                val user = User(userEmail,userName,firstName,lastName, password)
                                userViewModel.addUser(user)
                                val userLive = userViewModel.getUserByUsername(user.userName)
                                userViewModel.setCurrentUser(userLive)
                                navController.navigate("userProfile")
                            } else {
                                accountExistDialog = true
                            }
                        } else {
                            showDialog = true // Set the flag to show the dialog
                        }
                    } else {
                        showDialog = true // Set the flag to show the dialog
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Create Account")
            }

        }

        // Show dialog if flag is true
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Invalid Input") },
                text = {
                    Column {
                        Text("One or more fields are invalid. Please check your input.")
                        Text("Username: Does not contain any of the following characters: | {} <> / ? ; : , [] + - $ # % ^ & * ( ) ` ~ \" @")
                        Text("Password: Does not contain any of the following characters: | {} <> / ? ; : , [] + - $ # % ^ & * ( ) _ ` ~ \"")
                        Text("Email: Ex. '1234@email.com'")
                        }
                    }
                    ,
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(Color.Blue)
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        } else if(accountExistDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Account Exists Already") },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(Color.Blue)
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewSignUpPage(){
    val dummyUser = User("test@gmail.com", "test", "gang", "gang", "gang")
    val userLiveData = MutableLiveData<User>()
    userLiveData.value = dummyUser
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    dummyUserViewModel.setCurrentUser(userLiveData)
    signUpPage(rememberNavController(), dummyUserViewModel, rememberCoroutineScope())
}
