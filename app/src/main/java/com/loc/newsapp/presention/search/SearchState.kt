package com.loc.newsapp.presention.search

import androidx.paging.PagingData
import com.loc.newsapp.domin.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

data class SearchState(
    val searchQuery: String="",
    val articles: Flow<PagingData<Article>>?=null,

)