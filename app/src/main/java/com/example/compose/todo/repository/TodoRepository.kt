package com.example.compose.todo.repository

import com.example.compose.todo.data.Todo
import com.example.compose.todo.data.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository
@Inject
constructor(private val dao: TodoDao){

    suspend fun insert(todo: Todo) = withContext(Dispatchers.IO){
        dao.insert(todo)
    }

    fun getAllTodos(): Flow<List<Todo>> = dao.getAllTodos()
}