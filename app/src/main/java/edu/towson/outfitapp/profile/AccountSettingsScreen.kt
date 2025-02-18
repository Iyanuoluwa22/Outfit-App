package edu.towson.outfitapp.profile

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.data.*
import androidx.lifecycle.*
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.HelperFunctions.ShowProgressIndicator
import edu.towson.outfitapp.HelperFunctions.TheTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// In this AccountSettingsScreen function the Main Users username, first name, last name, and password are shown in textfields.
// theses textfields of information are mutable and can be changed. If the save changes button is clicked then the new information is
// saved to the database. There are also a cancel (that takes you back to the previous screen), logout (takes user back to login),
// and delete function (which deletes the account)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountSettingsScreen(navController: NavController, userViewModel: UserViewModel, viewModelScope: CoroutineScope) {
    val mainUser by userViewModel.mainUser.observeAsState()
    var newUsername by remember { mutableStateOf(mainUser?.userName) }
    var newPassword by remember { mutableStateOf(mainUser?.password) }
    var newFirstName by remember { mutableStateOf(mainUser?.firstName) }
    var newLastName by remember { mutableStateOf(mainUser?.lastName) }
    var showErrorPopup by remember { mutableStateOf(false) } // State for showing error popup
    var showDeleteConfirmation by remember { mutableStateOf(false) } // State for showing delete confirmation
    var showProgress by remember { mutableStateOf(false) }
    var showProgressLogin by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {TheTopBar(navController = navController)}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            val userDao = userViewModel.userDao
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
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            val validUsername = newUsername?.let { isValidUsername(it) }
                            val validPassword = newPassword?.let { isValidPassword(it) }
                            if (!validUsername!! || !validPassword!!) {
                                showErrorPopup = true // Show popup if username or password is invalid
                            } else {
                                viewModelScope.launch {
                                    newUsername?.let {
                                        mainUser?.userName?.let { oldUsername ->
                                            userDao.changeUsername(oldUsername, it)
                                        }
                                    }
                                    newPassword?.let {
                                        mainUser?.userName?.let { username ->
                                            userDao.changePassword(username, it)
                                        }
                                    }
                                    newFirstName?.let {
                                        mainUser?.userName?.let { username ->
                                            userDao.changeFirstName(username, it)
                                        }
                                    }
                                    newLastName?.let {
                                        mainUser?.userName?.let { username ->
                                            userDao.changeLastName(username, it)
                                        }
                                    }
                                    showProgress = true
                                }
                            }
                        },
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text("Save Changes")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            showProgressLogin = true
                        },
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text("Log Out")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            showDeleteConfirmation = true
                        },
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text("Delete Account")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            showProgress = true
                        },
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text("Cancel")
                    }

                }


            if (showProgress){
                ShowProgressIndicator(navController, "",true )
            } else if(showProgressLogin){
                ShowProgressIndicator(navController, route = "login", popUpTo = true, popUpToNav = "login" )
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
    }else if (showDeleteConfirmation) { // shows delete confirmation for when delete button is pressed.
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = {
                Text(text = "Delete Account?")
            },
            text = {
                Text(text = "Are you sure you want to delete your account?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        mainUser?.let { userViewModel.deleteUser(it) }
                        navController.navigate("login") {
                                popUpTo("login") { inclusive = true }
                        }
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDeleteConfirmation = false }
                ) {
                    Text("No")
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewAccountSettingsScreen() {
    val dummyUser = User("test@gmail.com", "test", "gang", "gang", "gang")
    val userLiveData = MutableLiveData<User>()
    userLiveData.value = dummyUser
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    dummyUserViewModel.setCurrentUser(userLiveData)
    AccountSettingsScreen(navController = rememberNavController(), dummyUserViewModel, rememberCoroutineScope())
}
