package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.dto.NewsApi
import com.loc.newsapp.data.remote.dto.SearchNewsPagingSource
import com.loc.newsapp.domin.model.Article
import com.loc.newsapp.domin.repostory.NewsRepository
import com.loc.newsapp.domin.repostory.RemotePagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
):NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
         return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                RemotePagingSource(newsApi = newsApi,
                    source=sources.joinToString(separator = ","))
            }

        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(searchQuery=searchQuery
                    ,api = newsApi,
                    sources =sources.joinToString(separator = ","))
            }

        ).flow
    }
}