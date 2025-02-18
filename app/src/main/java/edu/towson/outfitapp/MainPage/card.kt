package edu.towson.outfitapp.MainPage

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import edu.towson.outfitapp.DatabaseData.PostData.Post
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel

@Composable
fun ImageCard(post: Post, userViewModel: UserViewModel, postViewModel: PostViewModel, context: Context){

    // Created the variable that sees if the user clicked on a comment section:
    var lookingAtComments by remember { mutableStateOf(false) }

    // list of strings to hold the comments
    var comments by remember { mutableStateOf(listOf<String>()) }

    var fakeComments = listOf(
        " @Nu: This looks nice!",
        " @Chris: Love the shoes",
        " @David: W",
        " @James: I think you could use some more color"
    )

    // Card that wil display the Image, caption, price of the fit, likes, and comments.
    Card(
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = Color.White

        )
    ){
        // Column holding the image, and the other data
        Column{
            // Image URL
            val imageUrl = post.postUrl
            // creating the Image
            val painter = rememberAsyncImagePainter(model = imageUrl)
            // post description
            val description = post.postCaption
            // username of the poster
            val userName by userViewModel.getUserName(post.userEmail).observeAsState()
            // Number of likes the post has
            val likes = post.postLikeNum
            // cost of the fit in the post
            val cost = post.totalCost

            var like by remember {mutableStateOf(false)}
            var likedColor by remember {
                mutableStateOf(Color.Gray)
            }

            // Show the Username of the poster with a '@' in front of it.
            Text(
                modifier = Modifier.padding(5.dp),
                text = "@${userName}",
                color = Color.Black,
                fontSize = 19.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Display the posted image for the Post
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            // Display the description of the post
            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)
            ){
                // if there is a description, then add the description as a text.
                if (description != null) {
                    Text(
                        text = description,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
                // Display the cost of the fir under the description
                Text(
                    text = "Fit Cost:  $${cost}",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }

            // Create a row to put the like button, cost of the fit, and comment section next to eachother.
            Row(modifier = Modifier.padding(5.dp)){

                // Show the like button and also the number of likes the post has.
                TextButton(
                    onClick = {
                        like = !like    // Basic like and unlike feature
                        // if likes do exist.
                        if(like){
                            postViewModel.likePost(context, post.postId) // Also shows a notification.
                            likedColor = Color.Red

                        }
                        else {
                            likedColor = Color.Gray
                            postViewModel.unLikePost(post.postId)
                        }
                    }
                ){
                    // Row to store the like button
                    Row{
                        // Create the likes Icon.
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Like Button",
                            tint = likedColor
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        // Show the text of the likes
                        Text(
                            text = "Likes: ${likes}",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                // The comments button
                TextButton(
                    onClick = {
                        lookingAtComments = true
                    }
                ){
                    Icon(
                        imageVector = Icons.Filled.Comment,
                        contentDescription = "Comments",
                        tint = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Comments",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            // If the comment section is clicked ...
            if(lookingAtComments){
                CommentSection(
                    comments = fakeComments,
                    onDismiss = { lookingAtComments = false },
                    postComment = {newComment ->
                        fakeComments += "@Kevin: ${newComment}"
                    }
                )
            }

        }

    }
}

@Composable
fun CommentSection(
    // Pass the comment, and on dismiss command, and the post comments to allow the function to
    // post a new comment to the comment section.
    comments: List<String>,
    onDismiss: () -> Unit,
    postComment: (String) -> Unit
){
    // Variable that holds and passes a new comment if the user wants to create one.
    var newComment by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier.padding(10.dp),
            shape = MaterialTheme.shapes.small,
            color = Color.White
        ) {
            // Show the comment section:
            Column(
                modifier = Modifier.padding(.15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Comments Section: ",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
                // For each of the comments in a posts comments, show them vertically.
                comments.forEach { comment ->
                    Text(
                        text = comment,
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    // put space between comments.
                    Spacer(modifier = Modifier.height(3.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(

                ) {
                    // Create a text field that allows the user to input a new comment in it.
                    TextField(
                        value = newComment, // the value of the textbox is the new comment
                        onValueChange = { newComment = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { postComment(newComment) }),
                        modifier = Modifier.width(200.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    TextButton(
                        modifier = Modifier.clip(CircleShape),
                        onClick = {
                            postComment(newComment)
                            newComment = ""
                        },
                    ) {
                        Text("Post", color = Color.Cyan)
                    }
                }
            }
        }
    }
}

