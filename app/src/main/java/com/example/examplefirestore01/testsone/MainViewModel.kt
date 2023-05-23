package com.example.example01.testsone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _numerator = MutableStateFlow(1)
    val numerator : StateFlow<Int> = _numerator
    //  var numerator: Int = 1
    var data = mutableStateOf(Data())
    val num = mutableStateOf(numerator)
    private val repository = Repository()

    init {
        // The value entered here should be the one that has the UserScore
        fetchDataVariant(numerator.value)
    }

    fun insertTestData(){
        //     insertData(Data(1, "test1", "test1", "test1A", "test1B))
        //     insertData(Data(2, "test2", "test2", "test2A", "test2B))

    }

    fun insertData(data: Data) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data = data)
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchData(num.value)?.let {
                data.value = it
            }
        }
    }

    fun fetchDataVariant(nu: Int) {
        viewModelScope.launch {
            repository.fetchDataVariant(nu)?.let {
                data.value = it
            }
        }
    }

}