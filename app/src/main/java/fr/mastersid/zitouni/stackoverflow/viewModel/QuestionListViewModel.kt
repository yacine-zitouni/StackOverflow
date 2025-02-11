package fr.mastersid.zitouni.stackoverflow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.repository.QuestionResponse
import fr.mastersid.zitouni.stackoverflow.usecase.GetQuestionResponseFlowUseCase
import fr.mastersid.zitouni.stackoverflow.usecase.UpdateQuestionDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionListViewModel @Inject constructor(
    private val getQuestionResponseFlowUseCase: GetQuestionResponseFlowUseCase,
    private val updateQuestionDataUseCase: UpdateQuestionDataUseCase
): ViewModel(){

    private val _questionList: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    val questionList: LiveData<List<Question>> = _questionList

    private val _isUpdating = MutableLiveData(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage : LiveData<String?> = _errorMessage


    fun updateList(){
        viewModelScope.launch(Dispatchers.IO){
            updateQuestionDataUseCase()
        }

    }
    init {
        viewModelScope.launch(Dispatchers.IO){
            getQuestionResponseFlowUseCase().collect{ response ->
                when( response) {
                    is QuestionResponse.Pending -> _isUpdating.postValue(true)
                    is QuestionResponse.Success -> {
                        _questionList.postValue(response.list)
                        _isUpdating.postValue(false)
                    }
                    is QuestionResponse.Error -> {
                        _isUpdating.postValue(false)
                        _errorMessage.postValue( response.message )
                    }

                }                }
            }
        }

}