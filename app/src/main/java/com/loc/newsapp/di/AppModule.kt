package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manger.LocalUserMangerImpl
import com.loc.newsapp.data.remote.dto.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domin.manger.LocalUserManger
import com.loc.newsapp.domin.repostory.NewsRepository
import com.loc.newsapp.domin.usercases.appentry.AppEntryUsesCases
import com.loc.newsapp.domin.usercases.appentry.ReadAppEntry
import com.loc.newsapp.domin.usercases.appentry.SaveAppEntry
import com.loc.newsapp.domin.usercases.news.GetNews
import com.loc.newsapp.domin.usercases.news.NewsUseCase
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ):LocalUserManger = LocalUserMangerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        laUserManger: LocalUserManger
    ) = AppEntryUsesCases(
        readAppEntry = ReadAppEntry(laUserManger),
        saveAppEntry = SaveAppEntry(laUserManger)
    )
    @Provides
    @Singleton
    fun provideNewsApi ():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ):NewsRepository =NewsRepositoryImpl(newsApi)
    @Singleton
    @Provides
   fun  provideNewsCase(
        newsRepository: NewsRepository
   ):NewsUseCase{
       return NewsUseCase(
           getNews = GetNews(newsRepository)

       )
   }
}