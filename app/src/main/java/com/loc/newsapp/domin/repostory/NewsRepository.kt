package com.loc.newsapp.domin.repostory

import androidx.paging.PagingData
import com.loc.newsapp.domin.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

}