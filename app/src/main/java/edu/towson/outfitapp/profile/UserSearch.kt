package edu.towson.outfitapp.profile

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar
import edu.towson.outfitapp.HelperFunctions.observeOnce
import java.util.Locale



// In this UserSearch function a textfield is provided so the user can search the database for a user
// when a user is searched and the search icon is pressed specific users will be displayed
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserSearch(navController: NavController, userViewModel: UserViewModel){
    Scaffold(topBar = {
        TheTopBar(navController)
    },
        bottomBar = {
            TheBottomBar(navController = navController)
        }
    ) { innerPadding ->
        var userName by remember { mutableStateOf("") }
        var accountNF by remember { mutableStateOf(false) }
        var previewUsers by remember { mutableStateOf<List<User?>?>(null) }
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .padding(innerPadding), // Apply innerPadding
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = userName,
                    onValueChange = { userName = it.lowercase(Locale.getDefault()) },
                    label = { Text("Search User Name") },
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

                IconButton(
                    onClick = {
                        val usersWithPrefix = userViewModel.userDao.getUsersByUsernamePrefix(userName)

                        usersWithPrefix.observeOnce {users ->
                            if(users != null){
                                previewUsers = users
                            } else {
                                accountNF = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for user",
                        Modifier.size(50.dp)
                    )
                }
            }

            previewUsers?.let { users ->
                PreviewUsersWithPrefix(users,userViewModel,navController)
            }

            if(accountNF){
                AccountNotFoundDialog(onDismiss = {accountNF = false})
            }
        }
    }
}

// This function sifts through a list of users that have a certain prefix when searched
// and passes each user through a UserListItem function. A Lazy Column is in place for scrollability
@Composable
fun PreviewUsersWithPrefix(
    users: List<User?>,
    userViewModel: UserViewModel,
    navController: NavController
) {
    LazyColumn {
        items(users) { user ->
            user?.let {
                UserListItem(user = user,userViewModel,navController)
            }
        }
    }
}

// This function displays the users information within a row. The username, users first name and users last name is displayed
// the row is clickable, when clicked the navController is navigated to the ViewUser page, where you can see the page of the user clicked on
@Composable
fun UserListItem(user: User,
                 userViewModel: UserViewModel,
                 navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                userViewModel.resetViewingUser()
                val viewUser = userViewModel.getUserByUsername(user.userName)
                userViewModel.setViewingUser(viewUser)
                navController.navigate("viewUser")
            },
        color = Color.LightGray, // Set the background color
        shape = MaterialTheme.shapes.medium, // Optional: Apply a shape to the surface
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(5.dp),
                    tint = Color(4282002273)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Username")
                Text(text = user.userName)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "First Name")
                Text(text = user.firstName)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Last Name")
                Text(text = user.lastName)
            }
        }
    }
}


// This function just shows an alert dialog when an Account doesn't exist
@Composable
fun AccountNotFoundDialog(onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Account Not Found") },
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


// Just previews the UserSearchScreen
@Preview
@Composable
fun PreviewUserSearch(){
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    UserSearch(rememberNavController(),dummyUserViewModel)
}
