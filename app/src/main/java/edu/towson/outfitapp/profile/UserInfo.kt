package edu.towson.outfitapp.profile;

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
        val emailMust = "@|.com|.org|.edu|.net|.int|.gov".toRegex()
        var atCount = 0 // Counter for '@' symbols
        for ((numChar, index) in emailInput.indices.withIndex()) {
                val char = emailInput[index]
                if (char == '@') {
                        atCount++
                        if (atCount > 1) {
                                return false // More than one '@' symbol found
                        }
                        if (numChar == 0) {
                                return false // '@' symbol found at the beginning
                        }
                        // Check if the next character is a period
                        if (index < emailInput.length - 1 && emailInput[index + 1] == '.') {
                                return false // '@' symbol followed by a period
                        }
                }
        }
        return emailInput.matches(emailMust)
}

fun getTheUsername(): String {
        return username
} // this function will eventually change so that we get a user via username and return their info

fun changeUsername(userName : String){
        username = userName
}