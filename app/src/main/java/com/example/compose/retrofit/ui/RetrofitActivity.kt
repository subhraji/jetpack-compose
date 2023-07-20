package com.example.compose.retrofit.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.compose.retrofit.Post
import com.example.compose.retrofit.util.ApiState
import com.example.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity: ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    getData(mainViewModel = mainViewModel)
                }
            }
        }
    }

    @Composable
    fun eachRow(post: Post){
        Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)) {
            Text(text = post.body, modifier = Modifier.padding(10.dp),fontStyle = FontStyle.Italic)
        }
    }

    @Composable
    fun getData(mainViewModel: MainViewModel){
        when(val result = mainViewModel.response.value){
            is ApiState.Success->{
                LazyColumn{
                    items(result.data){response->
                        eachRow(post = response)
                    }
                }
            }
            is ApiState.Failure->{
                Text(text = "${result.msg}")
            }
            is ApiState.Loading->{
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            is ApiState.Empty->{

            }
        }
    }
}