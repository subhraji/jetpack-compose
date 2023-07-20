package com.example.compose

import android.os.Bundle
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.ui.theme.Shapes
import com.example.compose.utils.User
import com.example.compose.utils.dummyData

class RecyclerViewActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    recyclerView(users = dummyData())
                }
            }
        }
    }

    @Composable
    fun eachRaw(user: User){
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 2.dp
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Image(painter = painterResource(id = R.drawable.hacker),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp)
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))
                )
                Text(text = user.description,
                    modifier = Modifier
                    .padding(8.dp).align(Alignment.CenterVertically)
                )
            }
        }
    }

    @Composable
    fun recyclerView(users: List<User>){
        LazyColumn{
            items(users) { user ->
                eachRaw(user)
            }
        }
    }
}