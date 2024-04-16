package edu.towson.outfitapp.profile


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.login.getUsername

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.heightIn(10.dp,200.dp),
            colors = topAppBarColors(
                containerColor = Color.Cyan,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = "Outfitify",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(20.dp),
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center
                )
            }
        )
    },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
               theBottomBar(navController)
            }
        }) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .background(color = Color(0.914f, 0.898f, 0.898f, 1.0f))
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                ProfilePicture()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Username()
            }


            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                FollowersCount()
                FollowingCount()
            }

            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
fun ProfilePicture() {
    // Replace with image resource
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Profile Picture of user",
        Modifier.size(50.dp)
    )
}

@Composable
fun Username() {
    Text(text = getUsername(), color = Color.Black,
        fontFamily = FontFamily.Monospace)
}

@Composable
fun FollowersCount() {
    Text("Followers: 100", color = Color.Black,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun FollowingCount() {
    Text("Following: 100", color = Color.Black,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun UploadPhotoButton() {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Upload Photo",
            Modifier.size(50.dp)
        )
    }
}

@Composable
private fun theBottomBar(navController : NavController){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Column {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Upload Photo",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Explore Page",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            UploadPhotoButton()
        }
        Column {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "User Notifications",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            IconButton(
                onClick = {
                    navController.navigate("userProfile")
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Page",
                    Modifier.size(50.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen(navController = rememberNavController())
}
