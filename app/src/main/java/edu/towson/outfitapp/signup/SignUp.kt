package edu.towson.outfitapp.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import edu.towson.outfitapp.data.User
import edu.towson.outfitapp.data.addUser
import edu.towson.outfitapp.data.isValidEmail
import edu.towson.outfitapp.data.isValidPassword
import edu.towson.outfitapp.data.isValidUsername
import edu.towson.outfitapp.data.userExists
import edu.towson.outfitapp.viewmodel.UserViewModel
import java.util.Locale

@Composable
fun SignUpScreen(navController: NavController, userViewModel: UserViewModel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var accountExistsDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = "Sign Up",
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
        )
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
        )
        TextField(
            value = userName,
            onValueChange = { userName = it.lowercase(Locale.getDefault()) },
            label = { Text("User Name") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sign Up Button
            Button(
                onClick = {
                    // when Sign up button is clicked
                    val validUsername = isValidUsername(userName)
                    val validPassword = isValidPassword(password)
                    val validEmail = isValidEmail(email)
                    if (!validUsername || !validPassword || !validEmail) {
                        showDialog = true
                    } else if(userExists(userName)) accountExistsDialog = true
                    else{
                        val user = User(userName,password,firstName,lastName,email)
                        addUser(user)
                        userViewModel.setUser(user)
                        navController.popBackStack()
                        navController.navigate("userProfile")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            ) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.width(10.dp))
            // Cancel Button
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Cancel")
            }
        }
        if (showDialog) {
            ShowAlertDialog(onDismiss = { showDialog = false })
        } else if (accountExistsDialog) {
            showAccountExistsDialog(onDismiss = { accountExistsDialog = false })
        }
    }
}
@Composable
fun ShowAlertDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Invalid Credentials") },
        text = { Column {
            Text("The username, password, or email is invalid or empty.")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Username: Letters, Numbers, Special Characters(_ and !)")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Password: Letters, Numbers, Special Characters(@ and !)")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Email: ex. '1234@gmail.com'")
        }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
fun showAccountExistsDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Account Already Exists") },
        text = { Column {
            Text("Account with this username or email already exist")
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("OK")
            }
        }
    )
}

@Preview
@Composable
fun PreviewSignUpScreen(){
    val dummyUser = User("test", "test123", "John", "Doe", "john.doe@example.com")
    val dummyUserViewModel = UserViewModel().apply {
        setUser(dummyUser)
    }
    SignUpScreen(navController = rememberNavController(),dummyUserViewModel)
}