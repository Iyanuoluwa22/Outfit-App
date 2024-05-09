package edu.towson.outfitapp.MainPage

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

@Composable
fun ImageCard(post: String){

    // Created the variable that sees if the user clicked on a comment section:
    var lookingAtComments by remember { mutableStateOf(false) }

    // list of strings to hold the comments
    var comments by remember { mutableStateOf(listOf<String>()) }

    var fakeComments = listOf(
        " @Nu: This is TRASH!",
        " @Chris: Love the shoes",
        " @David: L"
    )

    // Card that wil display the Image, caption, price of the fit, likes, and comments.
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.Black

        )
    ){
        // Column holding the image, and the other data
        Column{
            // Image URL
            val imageUrl = post
            // creating the Image
            val painter = rememberAsyncImagePainter(model = imageUrl)
            // post description
            val description = "This jacket is FIRE!"
            // username of the poster
            val userName = "Kevin"
            // Number of likes the post has
            var likes by remember { mutableIntStateOf(756) }
            // cost of the fit in the post
            val cost = 3289

            // Show the USername of the poster with a '@' in front of it.
            Text(
                text = "@${userName}",
                color = Color.White,
                fontSize = 22.sp,
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

            // Display the descrtiption of the post
            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)
            ){
                Text(
                    text = description,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Fit Cost:  $${cost}",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }

            // Create a row to put the like button, cost of the fit, and comment section next to eachother.
            Row(modifier = Modifier.padding(5.dp)){

                // Show the cost
                TextButton(
                    onClick = {
                        // Change the number of likes that the Post has
                        likes++
                    }
                ){
                    Row{
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Like Button",
                            tint = Color.Red
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Likes: ${likes}",
                            color = Color.Cyan,
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
                        tint = Color.Green
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Comments",
                        color = Color.Cyan,
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
            color = Color.Gray
        ) {
            Column(
                modifier = Modifier.padding(.15.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Comments Section: ",
                    color = Color.Cyan,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))

                comments.forEach { comment ->
                    Text(
                        text = comment,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(

                ) {
                    TextField(
                        value = newComment,
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

