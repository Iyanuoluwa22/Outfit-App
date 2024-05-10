package edu.towson.outfitapp.DatabaseData.UserData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User) // How to add a single user

    @Query("SELECT * FROM users ORDER BY userEmail ASC ")  // Gets all users
    fun readAllData(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User) // How to delete a user

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers() // This deletes all user in the table (Please don't use unless you have too)

    @Query("SELECT userName FROM users WHERE userEmail = :userEmail") // gets a user username through their email
    fun getUsernameByEmail(userEmail: String): LiveData<String?>

    @Query("SELECT * FROM users WHERE userName = :username")
    fun getUserByUsername(username: String): LiveData<User?> // Gets one particular user based on user name

    @Query("SELECT * FROM users WHERE userName = :username AND password = :password")
    fun login(username: String, password: String): LiveData<User?> // This will be used to do login verification

    @Query("UPDATE users SET userName = :newUsername WHERE userName = :currentUsername")
    suspend fun changeUsername(currentUsername: String, newUsername: String)
    @Query("UPDATE users SET password = :newPassword WHERE userName = :username")
    suspend fun changePassword(username: String, newPassword: String)

    @Query("UPDATE users SET firstName = :newFirstName WHERE userName = :username")
    suspend fun changeFirstName(username: String, newFirstName: String)

    @Query("UPDATE users SET lastName = :newLastName WHERE userName = :username")
    suspend fun changeLastName(username: String, newLastName: String)

    @Query("SELECT * FROM users WHERE userName LIKE :username || '%' ORDER BY userName ASC")
    fun getUsersByUsernamePrefix(username: String): LiveData<List<User?>?>
}