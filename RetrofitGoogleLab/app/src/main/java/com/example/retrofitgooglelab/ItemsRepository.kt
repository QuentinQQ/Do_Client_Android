package com.example.retrofitgooglelab

class ItemsRepository {
    private val searchService = RetrofitObject.retrofitService
    private val API_KEY = "AIzaSyBQb3Ytyx8FrnJGehF1XcjacrLJZJnohew"
    private val SEARCH_ID_cx = "70687d496777443ea"

    suspend fun getResponse(keyword: String): SearchResponse {
        return searchService.customSearch(
            API_KEY,
            SEARCH_ID_cx,
            keyword
        )
    }
}