package dev.patrick.dncassignment.presentation.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.patrick.dncassignment.domain.model.User
import dev.patrick.dncassignment.domain.usecase.BaseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: BaseUseCase
) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    val users = _query.switchMap { query ->
        liveData(context = viewModelScope.coroutineContext) {
            emitSource(useCase.getUsers(query).asLiveData())
        }
    }

    val favoriteUsers = _query.switchMap { query ->
        liveData(context = viewModelScope.coroutineContext) {
            emitSource(useCase.getFavoriteUsers(query).asLiveData())
        }
    }

    fun search(query: String) {
        if (_query.value != query) {
            _query.value = query
        }
    }

    fun toggleFavorite(user: User, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                useCase.insertFavorite(user)
            } else {
                useCase.removeFavorite(user)
            }
        }
    }
}