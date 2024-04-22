package edu.towson.outfitapp.data

class User(
    var username : String,
    var password : String,
    var firstName : String,
    var lastName : String,
    var email : String) {

    private var followers : MutableList<User> = mutableListOf()
    private var following : MutableList<User> = mutableListOf()
    private var posts : MutableList<Post> = mutableListOf()
    fun changeUsername(newUsername : String){
        username = newUsername
    }

    fun changePassword(newPassword : String){
        password = newPassword
    }

    fun changeFirstName(newFirstName : String){
        firstName = newFirstName
    }

    fun changeLastName(newLastName : String){
        lastName = newLastName
    }

    fun addFollower(username: String){
        val user = getUser(username)
        if (user != null) followers.add(user)
    }

    fun deleteFollower(email: String){
        val user = getUser(email)
        if (user != null) followers.remove(user)
    }

    fun getFollowers(): MutableList<User> {
        return followers
    }

    fun addFollowing(email: String){
        val user = getUser(email)
        if (user != null) following.add(user)
    }

    fun deleteFollowing(email: String){
        val user = getUser(email)
        if (user != null) following.remove(user)
    }

    fun getFollowing(): MutableList<User> {
        return following
    }

}