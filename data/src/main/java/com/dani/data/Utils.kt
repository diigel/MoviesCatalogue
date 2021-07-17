package com.dani.data

fun Int?.nonNullable(): Int = this ?: 0
fun String?.nonNullable(): String = this ?: ""
