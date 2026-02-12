package com.pulak.todoapplication.data

import com.pulak.todoapplication.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository(
    private val todoDao: TodoDao
    ) {

    val todos: Flow<List<Todo>> =
        todoDao.getAllTodos().map { entities ->
            entities.map { it.toDomain() }
        }

    suspend fun addTodo(title: String) {
        if (title.isBlank()) return
        todoDao.insertTodo(
            TodoEntity(
                title = title.trim()
            )
        )
    }

    suspend fun toggleDone(todo: Todo) {
        val updated = TodoEntity(
            id = todo.id,
            title = todo.title,
            isDone = !todo.isDone,
            createdAt = todo.createdAt
        )
        todoDao.updateTodo(updated)
    }

    suspend fun deleteTodo(todo: Todo) {
        val entity = TodoEntity(
            id = todo.id,
            title = todo.title,
            isDone = todo.isDone,
            createdAt = todo.createdAt
        )
        todoDao.deleteTodo(entity)
    }
}

private fun TodoEntity.toDomain(): Todo =
    Todo(
        id = id,
        title = title,
        isDone = isDone,
        createdAt = createdAt
    )

