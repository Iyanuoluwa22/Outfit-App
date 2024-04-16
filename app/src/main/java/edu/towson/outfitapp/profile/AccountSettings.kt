package edu.towson.outfitapp.profile

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AccountSettings (navController: NavController){
    Text(text = "Wagwan")
}

@Preview
@Composable
fun PreviewAccountSettings(){
    AccountSettings(navController = rememberNavController())
}