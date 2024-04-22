package edu.towson.outfitapp.data


    private var allUsers : MutableList<User> = mutableListOf(User("test","1234","Test","Run","testrun@gmail.com"))

    fun userExists(username : String): Boolean{
        for (user in allUsers) {
            if (user.username.equals(username)) return true
        }
        return false
    }

    fun addUser(user: User){
        allUsers.add(user)
    }

    fun getUser(username: String) : User?{
        for (user in allUsers){
            if (user.username.equals(username)) return user
        }
        return null
    }

    fun changeUsername(oldUsername: String,newUsername : String){
        val user = getUser(oldUsername)
        if (user != null) {
            user.username = newUsername
        }
    }

    fun changePassword(username: String,password : String){
        val user = getUser(username)
        if (user != null) {
            user.password = password
        }
    }

    fun changeFirstName(username: String, firstName : String){
        val user = getUser(username)
        if (user != null) {
            user.firstName = firstName
        }
    }

    fun changeLastName(username: String, lastName : String){
        val user = getUser(username)
        if (user != null) {
            user.lastName = lastName
        }
    }

    fun deleteUser(email: String){
        for (user in allUsers){
            if(user.email.equals(email)) allUsers.remove(user)
        }
    }
