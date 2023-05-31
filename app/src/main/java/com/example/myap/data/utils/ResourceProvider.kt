package com.example.myap.data.utils

interface ResourceProvider {

    fun getString(id: Int): String

    fun getColor(id: Int): Int
}
