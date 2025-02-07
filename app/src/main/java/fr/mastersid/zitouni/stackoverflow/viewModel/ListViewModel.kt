package fr.mastersid.zitouni.stackoverflow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.repository.Repository
import fr.mastersid.zitouni.stackoverflow.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _questionList: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionList: LiveData<List<Question>> = _questionList

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage : LiveData<String?> = _errorMessage


    fun updateList(){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData()
        }

    }
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.response.collect{ response ->
                when( response) {
                    is Response.Pending -> _isUpdating.postValue(true)
                    is Response.Success -> {
                        _questionList.postValue(response.list)
                        _isUpdating.postValue(false)
                    }
                    is Response.Error -> {
                        _isUpdating.postValue(false)
                        _errorMessage.postValue( response.message )
                    }

                }                }
            }
        }

}