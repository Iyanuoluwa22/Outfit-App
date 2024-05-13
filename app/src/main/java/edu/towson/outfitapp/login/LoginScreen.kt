package edu.towson.outfitapp.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.observeOnce
import edu.towson.outfitapp.HelperFunctions.ShowProgressIndicator
import edu.towson.outfitapp.data.isValidPassword
import edu.towson.outfitapp.data.isValidUsername
import edu.towson.outfitapp.data.populateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel, postViewModel: PostViewModel) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var wrongPasswordDialog by remember { mutableStateOf(false) }
    var accountNotFoundDialog by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(tween(5000), RepeatMode.Reverse),
        label = "scale"
    )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
              ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Welcome to Outfitify",
                fontSize = 38.sp,
                modifier = Modifier
                    .padding(9.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    }
                    .align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(4282002273)

                )
            Spacer(modifier = Modifier.height(200.dp))

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                Modifier.size(50.dp),
                tint = Color(4282002273)
            )
            Text(
                text = "Login",
                fontSize = 30.sp,
                modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(4282002273)
            )
            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(
                value = userName,
                onValueChange = { userName = it.lowercase(Locale.getDefault()) },
                label = { Text("User Name") },
                modifier = Modifier.padding(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
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
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.padding(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
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
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                visualTransformation = PasswordVisualTransformation()
            )
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Login Button
                Button(
                    onClick = {
                        // new var
                        val populate = userViewModel.getAllUsers().value?.size
                        if (populate == 0){
                            populateData(userViewModel, postViewModel)
                        }
                        val user = userViewModel.loginCheck(userName, password)
                        val validUsername = isValidUsername(userName)
                        val validPassword = isValidPassword(password)
                        if (!validUsername || !validPassword) {
                            showDialog = true
                        } else {
                            val userLive = userViewModel.loginCheck(userName, password)
                            userLive.observeOnce { user ->
                                if (user != null) {
                                    userViewModel.setCurrentUser(userLive)
                                    showProgress = true
                                } else {
                                    accountNotFoundDialog = true
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                ) {
                    Text(text = "Login")
                }


                Spacer(modifier = Modifier.width(10.dp))
                // Sign-up Button
                Button(
                    onClick = {
                        val populate = userViewModel.getAllUsers().value?.size
                        if (populate == 0){
                            populateData(userViewModel, postViewModel)
                        }
                        navController.navigate("signUp") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Sign-Up")
                }
            }
            if (showDialog) {
                ShowAlertDialog(onDismiss = { showDialog = false })
            } else if (wrongPasswordDialog) {
                ShowWrongPasswordDialog(onDismiss = { wrongPasswordDialog = false })
            } else if (accountNotFoundDialog) {
                ShowAccountDialog(onDismiss = { accountNotFoundDialog = false })
            } else if (showProgress) {
                ShowProgressIndicator(navController, "userProfile")
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
            Text("Wrong Password or Account Does not Exist")
            Spacer(modifier = Modifier.height(5.dp))
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
    val dummyUser = User("test@gmail.com", "test", "gang", "gang", "gang")
    val userLiveData = MutableLiveData<User>()
    userLiveData.value = dummyUser
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    dummyUserViewModel.setCurrentUser(userLiveData)
   // LoginScreen(navController = rememberNavController(), dummyUserViewModel)
}

