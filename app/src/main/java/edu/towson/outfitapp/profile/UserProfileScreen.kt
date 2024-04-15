package edu.towson.outfitapp.profile


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen() {
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
    }) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
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
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                UploadPhotoButton()
            }

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
    Text("Username", color = Color.Black,
        fontFamily = FontFamily.Monospace)
}

@Composable
fun FollowersCount() {
    Text("Followers: 100", color = Color.Black)
}

@Composable
fun FollowingCount() {
    Text("Following: 50", color = Color.Black)
}

@Composable
fun UploadPhotoButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text("+", color = Color.White)
    }
}

@Preview
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen()
}
