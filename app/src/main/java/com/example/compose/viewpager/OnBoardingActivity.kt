package com.example.compose.viewpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.example.compose.ui.theme.ComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class OnBoardingActivity: ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface {
                    OnBoardingScreen()
                }
            }
        }
    }
}