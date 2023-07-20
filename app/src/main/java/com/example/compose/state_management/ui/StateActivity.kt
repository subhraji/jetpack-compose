package com.example.compose.state_management.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

class StateActivity: ComponentActivity() {
    private val viewModel: StateViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    showWidget()
                }
            }
        }
    }

    @Composable
    fun showWidget(){
        val name = rememberSaveable{mutableStateOf("")}

        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(viewModel.name.value.isNotEmpty()){
                Text(text = viewModel.name.value, color = Color.Blue)
            }

            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                label = {
                    Text(text = "Name")
                }
            )
        }
    }
}