package edu.towson.outfitapp.DatabaseData.PagesForTesting

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import edu.towson.outfitapp.DatabaseData.PostData.Post
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.CurrentUser
import java.time.LocalDate

//  THIS PAGE IS UNFINISHED SO

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImageUploadLogic(postViewModel: PostViewModel, currentUser: CurrentUser?) {


    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = it
    }
    // Observe the state of posts using observeAsState inside a composable function
    val postsState by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    var postOutfitTotalCost by remember { mutableStateOf("") }
    var postCaption by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false ) }
    val currentDate: LocalDate = LocalDate.now()

    Column(
        modifier = Modifier
            .padding(10.dp)
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
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = postCaption,
            onValueChange = { postCaption = it },
            label = { Text("Give your post a caption ") },
            modifier = Modifier.padding(15.dp),
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
            modifier = Modifier.padding(15.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Blue
            )
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
                        result.value?.let { image ->

                          // Gotta handle toInt problem
                            // logic to handle adding a new post

                            val post = Post(
                                "christianbastien020@gmail.com",
                                image.toString(),
                                currentDate.toString(),
                                postCaption,
                                0,
                                postOutfitTotalCost.toInt()
                            )
                            postViewModel.addPost(post)
                        }
                    } else {
                        showDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Add")
            }

            Button(
                onClick = {
                    postViewModel.deleteAllPost()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "delete")
            }

        // Must be deleted later only used for testing
        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            items(postsState) { post ->
                val imageUrl = post.postUrl
                val painter = rememberAsyncImagePainter(model = imageUrl)

                post.postUrl.let { Text(text = it.toString()) }

                Image(
                    painter = painter,
                    contentDescription = post.postCaption,
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .padding(40.dp)
                )

            }
        }

    }


}