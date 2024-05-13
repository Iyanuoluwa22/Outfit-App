package edu.towson.outfitapp.DatabaseData.PagesForTesting

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import edu.towson.outfitapp.DatabaseData.PostData.Post
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImageUploadLogic(navController: NavController,postViewModel: PostViewModel, userViewModel: UserViewModel) {
    val context = LocalContext.current
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {// This what actual lets a user pick a photo within the
        result.value = it  //
    }
    // Observe the state of posts using observeAsState inside a composable function
    val postsState by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    var postOutfitTotalCost by remember { mutableStateOf("") }
    var postCaption by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false ) }
    val currentDate: LocalDate = LocalDate.now()
    val currentUser = userViewModel.mainUser
    Scaffold(topBar = {TheTopBar(navController)},
        bottomBar = {TheBottomBar(navController)}){ innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {

            if(showDialog){
                MyDialog(
                    onDismiss = {showDialog = false},
                    onConfirm = {showDialog = false}
                )
            }


            Text(
                text = "Create a New Post ",
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = postCaption,
                onValueChange = { postCaption = it },
                label = { Text("Give your post a caption ") },
                modifier = Modifier.padding(15.dp),
                leadingIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Comment, contentDescription = null, Modifier.size(20.dp))
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Blue
                )
            )

            TextField(
                value = postOutfitTotalCost,
                onValueChange = { postOutfitTotalCost = it },
                label = { Text("Whats the total cost of your outfit?") },
                modifier = Modifier.padding(13.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Blue
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AttachMoney, contentDescription = null, Modifier.size(20.dp))
                },

                )


            Button(onClick = {
                launcher.launch(
                    PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                )

            }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Select an Image")
            }

            Button(
                onClick = {
                    if (postCaption.isNotEmpty() && postOutfitTotalCost.isNotEmpty() && result.value != null) {
                        result.value?.let { image -> // ensures all fields are not empty


                            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION  // This makes sure the image that we capture in the database is sticks around and doesn't disappear
                            context.contentResolver.takePersistableUriPermission(image, flag)

                            val post = currentUser.value?.let {
                                Post(  // Post that is created
                                    it.userEmail,
                                    image.toString(),
                                    currentDate.toString(),
                                    postCaption,
                                    0,
                                    postOutfitTotalCost.toInt()
                                )
                            }
                            if (post != null) {
                                postViewModel.addPost(post) // Post is then add
                            }
                        }
                    } else {
                        showDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Add")
            }

        }
    }

}