package edu.towson.outfitapp.DatabaseData.PagesForTesting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.outfitapp.DatabaseData.UserData.CurrentUser
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel

@Composable
fun signUpPage(userViewModel: UserViewModel) {

    val users by userViewModel.getAllUsers().observeAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showDialog by remember { mutableStateOf(false ) }


        Text(
            text = "Sign Up For Outfitify",
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
            label = { Text(text = "Last name") },
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = "Age", fontSize = 15.sp) },
            modifier = Modifier.padding(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )

        TextField(
            value = password,
            onValueChange = { password= it },
            label = { Text(text = "Password", fontSize = 15.sp) },
            modifier = Modifier.padding(10.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
        )
        Row(modifier = Modifier.padding(20.dp)){
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue))
            {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    // Ensure all fields are filled
                    if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty() && userName.isNotEmpty() && password.isNotEmpty() ) {
                        // Create a new user object
                        val user = User(0, userName, firstName, lastName, age.toInt(), password)
                        // Add user to database
                        userViewModel.addUser(user)
                        val tempCurrentUser = userViewModel.getUserByUsername(userName)


                        val currentUser = tempCurrentUser.value?.let { CurrentUser(it.id, userName, firstName, lastName, age.toInt(), password ) } // this will be passed around so we know who the current user is

                        // Clear input fields
                        firstName = ""
                        lastName = ""
                        age = ""
                        password = ""
                        userName = " "
                    } else {
                        showDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Add")
            }
        }


        if(showDialog){
            MyDialog(
                onDismiss = {showDialog = false},
                onConfirm = {showDialog = false}
            )
        }

        // LazyColumn to display users
        // Must delete later
        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            items(users) { user ->
                Text(
                    text = "${user.firstName} ${user.lastName} (${user.age} years old) ${user.id}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Button(onClick = { userViewModel.deleteAllUsers() }) {
            Text("Delete All Users")
        }

    }
}