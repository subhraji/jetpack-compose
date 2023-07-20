package com.example.compose.retrofit.util

import com.example.compose.retrofit.Post

sealed class ApiState{
    class Success(val data: List<Post>): ApiState()
    class Failure(val msg: Throwable): ApiState()
    object Loading: ApiState()
    object Empty: ApiState()
}
