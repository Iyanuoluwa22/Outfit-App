package edu.towson.outfitapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.outfitapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _mainUser = MutableStateFlow<User?>(null)
    var mainUser: StateFlow<User?> = _mainUser

    fun setUser(user: User) {
        viewModelScope.launch {
            _mainUser.emit(user)
        }
    }
}
