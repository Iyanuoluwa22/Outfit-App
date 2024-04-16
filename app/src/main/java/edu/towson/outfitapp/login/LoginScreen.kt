package edu.towson.outfitapp.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.profile.UserProfileScreen


public var userUserName : String = ""
@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
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

        TextField(
            value = userName,
            onValueChange = { userName = it
                            setUsername(userName)
                userName = userUserName
            },
            label = { Text("User Name") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, Modifier.size(20.dp))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            )
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.padding(10.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, Modifier.size(20.dp) )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue,
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Login Button
            Button(
                onClick = {
                    navController.navigate("userProfile")
                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),

                ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.width(10.dp) )
            // Sign-up Button
            Button(
                onClick = { navController.navigate("signUp") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Sign-Up")
            }
        }
    }
}

private fun setUsername(userName: String) {
    var modifiedUserName = ""
    for (char in userName) {
        if (char == ' ') {
            modifiedUserName += ""
        } else {
            modifiedUserName += char
        }
    }
    if (modifiedUserName.isBlank()) {
        userUserName = "Username"
    } else {
        userUserName = modifiedUserName
    }
}

fun getUsername(): String{
    return userUserName
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}


