package com.dani.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.buffer
import okio.source
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

internal fun MockWebServer.openFile(file : String) : BufferedSource? {
    val input = javaClass.classLoader?.getResourceAsStream("responses/$file")
    return input?.source()?.buffer()
}

internal fun MockWebServer.mockResponse(file: String,code : Int){
    val source = openFile(file)
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time : Long = 2,
    timeUnit: TimeUnit= TimeUnit.SECONDS
) : T {
    var data : T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T?) {
            if (t != null) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
    }
    this.observeForever(observer)

    // fot wait indefinitely if the liveData is not set.
    if (!latch.await(time,timeUnit) || data == null ) {
        throw  TimeoutException("Timeout, data is null")
    }

    return data as T
}