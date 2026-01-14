package com.example.bookstore.ui.home

import androidx.lifecycle.ViewModel
import com.example.bookstore.data.model.Book
import com.example.bookstore.data.repository.BookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val repository: BookRepository
) : ViewModel() {

    sealed class UiState {
        object Loading : UiState()
        data class Success(val books: List<Book>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun loadBooks(showPopular: Boolean) {
        _uiState.value = UiState.Loading

        repository.fetchBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                val books =
                    if (showPopular) result.first else result.second

                _uiState.value = UiState.Success(books)
            }, { error ->
                _uiState.value =
                    UiState.Error(error.message ?: "Something went wrong")
            })
    }
}
