package edu.towson.outfitapp.HelperFunctions


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import kotlinx.coroutines.delay

fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            observer(value)
            removeObserver(this)
        }
    })
}

@Composable
fun ShowProgressIndicator(navController: NavController,route : String, popBack : Boolean = false) {
    // Display CircularProgressIndicator
    CircularProgressIndicator(
        trackColor = Color.Blue
    )
    // Start a coroutine to delay hiding the progress indicator
    LaunchedEffect(key1 = true) {
        delay(1000) // Delay for 1 seconds
        if(popBack){
            navController.popBackStack()
        }else{
            navController.navigate(route)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheTopBar(navController: NavController){
    TopAppBar(
        modifier = Modifier.heightIn(10.dp,200.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
        ),
        title = {
            Text(text = "Outfitify",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(25.dp),

                color = Color.White,

                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            IconButton(
                onClick = { navController.navigate("accountSettings") },
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
        }
    )
}



@Composable
fun TheBottomBar(navController : NavController){
    BottomAppBar(
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            Column {
                IconButton(
                    onClick = {
                        if (navController.currentDestination?.route != "userFeed") {
                            navController.navigate("userFeed")
                        }
                              },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "User Feed",
                        Modifier.size(50.dp)
                    )
                }
            }
            Column {
                IconButton(
                    onClick = {if (navController.currentDestination?.route != "userSearch") {
                        navController.navigate("userSearch")
                    }
                              },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search for user",
                        Modifier.size(50.dp)
                    )
                }
            }
            Column {
                IconButton(
                    onClick = {
                        if (navController.currentDestination?.route != "imageUpload") {
                            navController.navigate("imageUpload")
                        }
                    },
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
                        if (navController.currentDestination?.route != "userProfile") {
                            navController.navigate("userProfile")
                        }
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

}