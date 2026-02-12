package com.pulak.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pulak.todoapplication.data.TodoDatabase
import com.pulak.todoapplication.data.TodoRepository
import com.pulak.todoapplication.ui.TodoApp
import com.pulak.todoapplication.ui.TodoViewModel
import com.pulak.todoapplication.ui.theme.TodoappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoappTheme {
                val db = TodoDatabase.getInstance(applicationContext)
                val repository = TodoRepository(db.todoDao())
                val factory = TodoViewModelFactory(repository)
                val viewModel: TodoViewModel = viewModel(factory = factory)
                TodoApp(viewModel = viewModel)
            }
        }
    }
}

class TodoViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
