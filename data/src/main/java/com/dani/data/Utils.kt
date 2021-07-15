package com.dani.data


//fun <T>T.nonNullable(default: T): T {
//    return if (this == null) {
//
//    }
//}

fun Int?.nonNullable(): Int = this ?: 0
fun String?.nonNullable(): String = this ?: ""
