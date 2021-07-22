package com.dani.data

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*

fun Any.toJson(): String {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    return gson.toJson(this)
}

fun <T> MutableList<T>.removeDuplicatesItem(): MutableList<T> {
    val set = LinkedHashSet<T>()
    set.addAll(this)
    this.clear()
    this.addAll(set)
    return this
}

fun ImageView.loadImage(
    url: String? = null,
    @DrawableRes drawableRes: Int? = null
) {
    if (drawableRes != null) {
        Glide.with(context)
            .load(drawableRes)
            .into(this)
    } else {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}

fun Context.loaderDialog(): AlertDialog {
    val builderProgress = AlertDialog.Builder(this)
        .setView(LayoutInflater.from(this).inflate(R.layout.loader_layout, null))
        .setCancelable(false)

    val dialogProgress = builderProgress.create()
    dialogProgress.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    return dialogProgress
}

fun EditText.watcher(scope: CoroutineScope = MainScope(), result: (String) -> Unit) {
    val watcher = object : TextWatcher {
        private var searchFor = ""
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            searchFor = searchText

            scope.launch {
                delay(1000)
                if (searchText != searchFor)
                    return@launch

                if (searchText.length >= 2) {
                    result.invoke(searchText)
                }
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    addTextChangedListener(watcher)
}