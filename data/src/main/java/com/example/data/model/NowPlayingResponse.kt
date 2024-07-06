package com.example.data.model

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)