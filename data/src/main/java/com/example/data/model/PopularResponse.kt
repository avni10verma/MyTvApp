package com.example.data.model

data class PopularResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)