package com.example.data.model

import com.example.data.model.MovieResult

data class SearchResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)