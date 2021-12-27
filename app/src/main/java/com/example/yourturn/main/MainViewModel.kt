package com.example.yourturn.main

import android.accounts.NetworkErrorException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourturn.MainActivity
import com.example.yourturn.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException

data class MainState(
    val restrictionList: List<User> = listOf(),
    val title: String = "",
    val error: Exception? = null
)

class MainViewModel() : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: Flow<MainState> = _state.asStateFlow()



    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            /* val list = mutableListOf<Restriction>()
             repeat(10) {
                 list.add(Restriction("resc $it", "description", it))
             }
             _state.value = _state.value.copy(restrictionList = list)*/
            val userList = MainActivity.AUTH_MANAGER.getUsers() ?: return@launch
            _state.value = _state.value.copy(restrictionList = userList)
        }
    }

    fun wipeError() {
        _state.value = _state.value.copy(error = null)
    }

    fun removeData(name: String) {
        viewModelScope.launch {
            val result = MainActivity.AUTH_MANAGER.removeUser()
            if (result) {
                val newList = _state.value.restrictionList.filter { it._id != name }
                _state.value = _state.value.copy(restrictionList = newList)
            } else {
                _state.value = _state.value.copy(error = NetworkErrorException())
            }
        }
    }
}