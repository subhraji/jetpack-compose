package com.example.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class LoginActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    loginScreen()
                }
            }
        }
    }

    @Preview
    @Composable
    fun loginScreen(){
        val username = remember { mutableStateOf("")}
        val password = remember { mutableStateOf("")}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hello Again!", color = Color.Blue, fontSize = 25.sp,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Text(text = "Welcome", color = Color.Blue, fontSize = 25.sp,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            Text(text = "Back", color = Color.Blue, fontSize = 25.sp,
                fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                label = {
                    Text(text = "Username")
                },
                placeholder = {
                    Text(text = "enter username")
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "password")
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "enter password")
                },
                modifier = Modifier.fillMaxWidth()

            )

            OutlinedButton(
                onClick = {
                login(username.value,password.value)
                          },
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                Text(text = "Login") }
        }
    }

    private fun login(username: String, password: String){
        if(username == "jks" && password == "123"){
            Toast.makeText(this,"Logged in successfully",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Wrong credential!",Toast.LENGTH_SHORT).show()
        }
    }
}