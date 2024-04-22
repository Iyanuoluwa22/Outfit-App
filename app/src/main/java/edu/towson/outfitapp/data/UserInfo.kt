package edu.towson.outfitapp.data;

var firstName : String = ""
var lastName : String = ""
var userBio: String = ""
var username: String = ""
var password : String = ""
var email : String = ""

fun isValidUsername(usernameInput:String) : Boolean {
        var invalidUsernameChar = charArrayOf('|','{','}','<',
                '>','/','?',';',':',',','[',']','+','-','$','#',
                '%','^','&','*','(',')','`','~','"','@',' ')
        if (usernameInput.equals("")) return false
        for (char in usernameInput) {
                for (el in invalidUsernameChar){
                        if (char.equals(el)) return false
                }
        }
        username = usernameInput
        return true
}

fun isValidPassword(passwordInput : String) : Boolean {
        var invalidPasswordChar = charArrayOf('|','{','}','<',
                '>','/','?',';',':',',','[',']','+','-','$','#',
                '%','^','&','*','(',')','_','`','~','"', ' ')
        if (passwordInput.equals("")) return false
        for(char in passwordInput){
                for (el in invalidPasswordChar){
                        if(char.equals(el)) return false
                }
        }
        return true
}

fun isValidEmail(emailInput: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        return emailInput.matches(emailRegex)
}