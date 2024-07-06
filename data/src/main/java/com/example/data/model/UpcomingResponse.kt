package com.example.data.model

data class UpcomingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)