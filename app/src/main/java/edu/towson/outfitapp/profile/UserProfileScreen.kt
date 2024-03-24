package edu.towson.outfitapp.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture()
        Spacer(modifier = Modifier.height(16.dp))
        Username()
        Spacer(modifier = Modifier.height(16.dp))
        FollowersCount()
        Spacer(modifier = Modifier.height(16.dp))
        FollowingCount()
        Spacer(modifier = Modifier.height(16.dp))
        UploadPhotoButton()
    }
}

@Composable
fun ProfilePicture() {
    // Replace defaultProfileImage with your image resource
    Text("Profile Picture Placeholder", color = Color.Black)
}

@Composable
fun Username() {
    Text("Username", color = Color.Black)
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
        onClick = { /* Handle upload photo button click */ },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text("Upload Photo", color = Color.White)
    }
}

@Preview
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen()
}
