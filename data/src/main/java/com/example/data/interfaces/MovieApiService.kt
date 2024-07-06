// ApiService.kt
package com.example.data.interfaces

import com.example.data.model.MovieResponse
import com.example.data.model.NowPlayingResponse
import com.example.data.model.PopularResponse
import com.example.data.model.SearchResponse
import com.example.data.model.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApiService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): MovieResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): PopularResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): UpcomingResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("search/movie")
    suspend fun searchMovies() : SearchResponse

    companion object {
        fun create(): MovieApiService {
            return RetrofitInstance.instance.create(MovieApiService::class.java)
        }
    }
}



