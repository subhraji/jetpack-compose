package com.example.compose.todo.ui.activity

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.compose.todo.data.Todo
import com.example.compose.todo.ui.view_model.TodoViewModel
import com.example.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodoActivity: ComponentActivity() {
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    addToolbar()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    @Preview
    fun addToolbar(){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TODO APP")
                    }
                )
            },
            floatingActionButton = {
                val openDialog = remember { mutableStateOf(false) }
              FloatingActionButton(onClick = {
                  openDialog.value = true
              }) {
                  addDialogBox(openDialog = openDialog)
                  Icon(Icons.Default.Add, contentDescription = "Add")
              }
            }
        ) {
            recyclerView(todoViewModel = viewModel)
        }
    }

    @Composable
    fun addDialogBox(openDialog:MutableState<Boolean>){
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("")}

        if(openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "ToDo")
                },
                text = {
                    Column(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        OutlinedTextField(
                            value = title.value,
                            onValueChange = {
                                title.value = it
                            },
                            placeholder = {
                                Text(text = "title")
                            },
                            label = {
                                Text(text = "Enter title")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                        OutlinedTextField(
                            value = description.value,
                            onValueChange = {
                                description.value = it
                            },
                            placeholder = {
                                Text(text = "Description")
                            },
                            label = {
                                Text(text = "Enter description")
                            },
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                },
                confirmButton = {
                    OutlinedButton(onClick = {
                        insert(title,description)
                        openDialog.value = false
                    }) {
                        Text(text = "Save")
                    }
                }
            )
        }
    }

    private fun insert(title: MutableState<String>,description: MutableState<String>){
        lifecycleScope.launchWhenStarted {
            if(!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)){
                viewModel.insert(
                    Todo(
                        title.value,
                        description.value,
                    )
                )
                Toast.makeText(this@TodoActivity,"Inserted...", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TodoActivity,"All fields are mandatory.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun eachRow(todo: Todo){
        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(4.dp)
        ){
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = todo.title, fontWeight = FontWeight.ExtraBold)
                Text(text = todo.description, fontStyle = FontStyle.Italic)
            }
        }
    }

    @Composable
    fun recyclerView(todoViewModel: TodoViewModel){
        LazyColumn{
            items(todoViewModel.response.value){ todo->
                eachRow(todo = todo)
            }
        }
    }

    private fun getUniqueUuid(): String {
        return System.currentTimeMillis().toString()
    }
}