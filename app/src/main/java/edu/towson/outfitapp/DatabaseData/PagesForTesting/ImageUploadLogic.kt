package edu.towson.outfitapp.DatabaseData.PagesForTesting

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import edu.towson.outfitapp.DatabaseData.PostData.Post
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel

//  THIS PAGE IS UNFINISHED SO

@Composable
fun ImageUploadLogic(postViewModel: PostViewModel) {
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = it
    }

    // Observe the state of posts using observeAsState inside a composable function

    val postsState by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    //val users by userViewModel.getAllUsers().observeAsState(initial = emptyList())

    Column {


        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Select Image")
        }

        Button(
            onClick = {
                result.value?.let { image ->
                    // Add logic to handle adding a new post
                    val post = Post(2, image.toString(),"4/26/2024", "First post", 0)
                    postViewModel.addPost(post)
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


        LazyColumn(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            items(postsState) { post ->
                val imageUrl = post.postUrl
                val painter = rememberAsyncImagePainter(model = imageUrl)

                post.postUrl.let { Text(text = it.toString()) }

                Log.d("ImageUploadLogic", "imageUrl: $imageUrl")

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