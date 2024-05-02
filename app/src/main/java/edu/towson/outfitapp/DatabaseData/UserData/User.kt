package edu.towson.outfitapp.DatabaseData.UserData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName:String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val password: String

)