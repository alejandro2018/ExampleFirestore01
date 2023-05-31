package com.example.examplefirestore01.testsone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class MainViewModel @AssistedInject constructor(
    @Assisted
    numerito: Int
) : ViewModel() {

    private val _numerator = MutableStateFlow(numerito)
    val numerator : StateFlow<Int> = _numerator
    //  var numerator: Int = 1
    var data = mutableStateOf(Data())
    val num = mutableStateOf(numerator)
    private val repository = Repository()

    init {
        // The value entered here should be the one that has the UserScore
        fetchDataVariant(numerator.value)
    }

    @AssistedFactory
    interface Factory {
        fun create(numerito: Int) : MainViewModel
    }

    companion object {
        fun provideMainViewModelFactory(factory: Factory, numerito: Int) : ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(numerito) as T
                }
            }
        }
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