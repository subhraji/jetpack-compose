package com.example.compose.viewpager

import androidx.annotation.DrawableRes
import com.example.compose.R

data class Pager(
    @DrawableRes val image: Int,
    val des: String
)

val dataList = listOf(
    Pager(R.drawable.ic_baseline_connected_tv_24,"PAGE ONE"),
    Pager(R.drawable.ic_baseline_kitesurfing_24,"PAGE TWO"),
    Pager(R.drawable.ic_android_black_24dp,"PAGE THREE")
)