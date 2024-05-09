package edu.towson.outfitapp.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar
import java.util.Locale
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserSearch(navController: NavController){
    Scaffold(topBar = {
        TheTopBar(navController)
    },
        bottomBar = {
            TheBottomBar(navController = navController)
        }
    ) { innerPadding ->
        var userName by remember { mutableStateOf("") }
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
                    //keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )

                IconButton(
                    onClick = {},
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
        }
    }
}

@Preview
@Composable
fun PreviewUserSearch(){
    UserSearch(rememberNavController())
}
