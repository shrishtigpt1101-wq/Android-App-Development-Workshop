package com.pulak.todoapplication.domain

data class Todo(
    val id: Int,
    val title: String,
    val isDone: Boolean,
    val createdAt: Long
)

