package com.pulak.todoapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulak.todoapplication.data.TodoRepository
import com.pulak.todoapplication.domain.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val newTodoTitle: String = ""
)

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.todos.collect { todos ->
                _uiState.update { it.copy(todos = todos) }
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(newTodoTitle = newTitle) }
    }

    fun onAddTodo() {
        val title = _uiState.value.newTodoTitle.trim()
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.addTodo(title)
            _uiState.update { it.copy(newTodoTitle = "") }
        }
    }

    fun onToggleTodo(todo: Todo) {
        viewModelScope.launch {
            repository.toggleDone(todo)
        }
    }

    fun onDeleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}

