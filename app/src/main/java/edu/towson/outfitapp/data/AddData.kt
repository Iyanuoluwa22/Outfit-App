package edu.towson.outfitapp.data

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.towson.outfitapp.DatabaseData.PostData.Post
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel


@Composable
fun AddData(userViewModel: UserViewModel, postViewModel: PostViewModel){

    // Please Only push each button once. Any more will crash your app and you'll need to reset
    Column {
            Button(onClick = {
                userViewModel.addUser( User("JacksBest@gmail.com", "JackBest010", "Jack", "Rider", "1234553"))
                userViewModel.addUser( User("MightDuck1@yahoo.com", "DuckKnight", "Jeff", "Cutless", "1234555"))
                userViewModel.addUser( User("DanielSmith70@gmail.com", "Daniel_Smith0", "Daniel", "Smith", "1723455"))
                userViewModel.addUser( User("Demi070@gmail.com", "Demikaze", "Demi", "Fregson", "12345r335"))
                userViewModel.addUser( User("CharlieBests@hotmail.com", "Bye_Charlie", "Charlie", "Williams", "123455"))
                userViewModel.addUser( User("DavidJones21@gmail.com", "DavyJones", "David", "Jones", "rrtvf455"))
                userViewModel.addUser( User("MaxBrown23@gmail.com", "Madder_Max", "Max", "Brown", "123rr455")) }) {
                Text(text = "Add Users")
            }

        Button(onClick = {
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "JacksBest@gmail.com",
                        "https://www.thefashionisto.com/wp-content/uploads/2023/07/Casual-Dress-Men-Chinos-Outfit.jpg",
                        "5/4/2024",
                        "Causal Wear",
                        24,
                        120
                    )
                )
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "MightDuck1@yahoo.com",
                        "https://i.pinimg.com/736x/45/eb/ac/45ebac4b30ec480c0c202e739a2bbb11.jpg",
                        "4/12/2024",
                        "Formal Wear",
                        55,
                        300
                    )
                )
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "DanielSmith70@gmail.com",
                        "https://www.azypo.com/wp-hx74pk/wp-content/uploads/2024/02/baggy-streetwear.jpg",
                        "3/2/2024",
                        "Street Wear",
                        102,
                        250
                    )
                )
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "Demi070@gmail.com",
                        "https://img.freepik.com/premium-photo/streetwear-outfit-with-bold-colors-patterns-standout-look-created-with-generative-ai_419341-4300.jpg",
                        "4/12/2024",
                        "Street Wear",
                        22,
                        150
                    )
                )
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "CharlieBests@hotmail.com",
                        "https://www.merricksart.com/wp-content/uploads/2023/03/jeans-swweaters-high-tops.jpg",
                        "4/12/2024",
                        "School Wear",
                        31,
                        23
                    )
                )
                postViewModel.addPost(
                    edu.towson.outfitapp.DatabaseData.PostData.Post(
                        "DavidJones21@gmail.com",
                        "https://www.instyle.com/thmb/IMyT6mfzkcxUuIPM9XD_e4zKeqE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-1334734881-2000-3707e065bb1447d3a728f4f58f4b81ed.jpg",
                        "1/7/2024",
                        "Beach Wear",
                        55,
                        300
                    )
                )
                postViewModel.addPost(Post("MaxBrown23@gmail.com", "https://hips.hearstapps.com/hmg-prod/images/summer-workwear-outfits-6-649c61622d89a.jpg", "3/2/2024", "Just a Fit", 15, 250))
            }) {
                Text(text = "Add posts ")
            }



        }




}