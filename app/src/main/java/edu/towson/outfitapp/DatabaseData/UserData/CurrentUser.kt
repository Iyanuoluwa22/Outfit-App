package edu.towson.outfitapp.DatabaseData.UserData

data class CurrentUser (
    val id: Int,
    val userName:String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val password: String
)