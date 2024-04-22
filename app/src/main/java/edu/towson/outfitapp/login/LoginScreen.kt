package edu.towson.outfitapp.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.data.getUser
import edu.towson.outfitapp.data.isValidPassword
import edu.towson.outfitapp.data.isValidUsername
import edu.towson.outfitapp.data.userExists
import java.util.Locale


@Composable
fun LoginScreen(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var wrongPasswordDialog by remember { mutableStateOf(false) }
    var accountNotFoundDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            Modifier.size(50.dp)
        )
        Text(
            text = "Login",
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
        )
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = userName,
            onValueChange = { userName = it.lowercase(Locale.getDefault())},
            label = { Text("User Name") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()})
            ,
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Login Button
            Button(
                onClick = {
                    val validUsername = isValidUsername(userName)
                    val validPassword = isValidPassword(password)
                    if (!validUsername || !validPassword) {
                        showDialog = true
                    } else if(userExists(userName)){
                        val user = getUser(userName)
                        if (user != null) {
                            if(user.password == password){
                                navController.popBackStack()
                                navController.navigate("userProfile")
                            }else{
                                wrongPasswordDialog = true
                            }
                        }
                    }else{
                        accountNotFoundDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.width(10.dp))
            // Sign-up Button
            Button(
                onClick = { navController.navigate("signUp") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Sign-Up")
            }
        }
        if (showDialog) {
            ShowAlertDialog(onDismiss = { showDialog = false })
        } else if(wrongPasswordDialog){
            ShowWrongPasswordDialog(onDismiss = { wrongPasswordDialog = false })
        } else if(accountNotFoundDialog){
            ShowAccountDialog(onDismiss = { accountNotFoundDialog = false })
        }
    }
}

@Composable
fun ShowAlertDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Invalid Credentials") },
        text = { Column {
            Text("The username or password is invalid or empty.")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Username: Letters, Numbers, Special Characters(_ and !)")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Password: Letters, Numbers, Special Characters(@ and !)")
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
fun ShowWrongPasswordDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Wrong Password Entered") },
        text = { Column {
            Text("The password is invalid or empty.")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Password: Letters, Numbers, Special Characters(@ and !) *No Spaces*")
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
fun ShowAccountDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Account Not Found") },
        text = { Column {
            Text("Account with this username does not exist")
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Username: Letters, Numbers, Special Characters(_ and !) *No Spaces*")
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
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}
