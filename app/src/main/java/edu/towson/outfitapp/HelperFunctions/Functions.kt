package edu.towson.outfitapp.HelperFunctions


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
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