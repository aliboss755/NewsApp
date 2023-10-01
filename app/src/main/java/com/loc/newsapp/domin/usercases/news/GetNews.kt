package com.loc.newsapp.domin.usercases.news

import androidx.paging.PagingData
import com.loc.newsapp.domin.model.Article
import com.loc.newsapp.domin.repostory.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews (
    private val newsRepository: NewsRepository
        ){
    operator fun invoke(sources:List<String>):Flow<PagingData<Article>>{
        return  newsRepository.getNews(sources=sources)
    }
}