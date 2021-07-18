package com.dani.favorites

object Constant {
    val movie1 = MovieEntity(
        id = 1,
        movieId = 851438,
        originalTitle = "Help",
        overview = "A painful break up prompts Grace to visit her friend Liv who is living in the idyllic English countryside with her boyfriend Edward and his dog Polly. The trio start the weekend in high spirits but it soon turns into chaos, as well-kept secrets are exposed and the friends come to see each other in a whole new light.",
        posterPath = "/7TSZqK7H0OF8k6GuRNwWIprlBo9.jpg",
        millis = System.currentTimeMillis()
    )
    val movie2 = MovieEntity(
        id = 2,
        movieId = 851447,
        originalTitle = "Lockdown 2.0",
        overview = " Lockdown overview",
        posterPath = "/bEcHI328Hs62itbUbuhFUeF0wn4.jpg",
        millis = System.currentTimeMillis()
    )
    val movie3 = MovieEntity(
        id = 3,
        movieId = 851446,
        originalTitle = "Lost Love",
        overview = "lost love overview",
        posterPath = "/7TSZqK7H0OF8k6GuRNwWIprlBo9.jpg",
        millis = System.currentTimeMillis()
    )
    val movie4 = MovieEntity(
        id = 4,
        movieId = 851445,
        originalTitle = "Land der Bitterkeit und des Stolzes",
        overview = "Land der Bitterkeit und des Stolzes overview",
        posterPath = "",
        millis = System.currentTimeMillis()
    )

    val listMovieEntity = listOf(movie1, movie2, movie3, movie4)
}