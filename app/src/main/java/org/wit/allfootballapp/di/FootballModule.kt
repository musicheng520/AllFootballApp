package org.wit.allfootballapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.wit.allfootballapp.data.local.news.NewsDao
import org.wit.allfootballapp.data.local.news.NewsDatabase
import org.wit.allfootballapp.data.local.news.NewsTypeConverter
import org.wit.allfootballapp.data.local.user.UserDao
import org.wit.allfootballapp.data.local.user.UsersDatabase
import org.wit.allfootballapp.data.manager.LocalUserManagerImpl
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.data.remote.NewsApi
import org.wit.allfootballapp.data.repository.CoachRepositoryImpl
import org.wit.allfootballapp.data.repository.FixtureRepositoryImpl
import org.wit.allfootballapp.data.repository.NewsRepositoryImpl
import org.wit.allfootballapp.data.repository.PlayerInfoRepositoryImpl
import org.wit.allfootballapp.data.repository.PlayerRepositoryImpl
import org.wit.allfootballapp.data.repository.TeamRepositoryImpl
import org.wit.allfootballapp.data.repository.TransferRepositoryImpl
import org.wit.allfootballapp.data.repository.UserRepositoryImpl
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.repository.CoachRepository
import org.wit.allfootballapp.domain.repository.FixtureRepository
import org.wit.allfootballapp.domain.repository.NewsRepository
import org.wit.allfootballapp.domain.repository.PlayerInfoRepository
import org.wit.allfootballapp.domain.repository.PlayerRepository
import org.wit.allfootballapp.domain.repository.TeamRepository
import org.wit.allfootballapp.domain.repository.TransferRepository
import org.wit.allfootballapp.domain.repository.UserRepository
import org.wit.allfootballapp.domain.usecase.app_entry.AppEntryUseCases
import org.wit.allfootballapp.domain.usecase.app_entry.ReadAppEntry
import org.wit.allfootballapp.domain.usecase.app_entry.SaveAppEntry
import org.wit.allfootballapp.domain.usecase.news.DeleteArticle
import org.wit.allfootballapp.domain.usecase.news.GetNews
import org.wit.allfootballapp.domain.usecase.news.NewsUseCases
import org.wit.allfootballapp.domain.usecase.news.SearchNews
import org.wit.allfootballapp.domain.usecase.news.SelectArticle
import org.wit.allfootballapp.domain.usecase.news.SelectArticles
import org.wit.allfootballapp.domain.usecase.news.UpsertArticle
import org.wit.allfootballapp.util.Constants.FOOTBALL_API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import org.wit.allfootballapp.util.Constants.FOOTBALL_BASE_URL
import org.wit.allfootballapp.util.Constants.NEWS_BASE_URL
import org.wit.allfootballapp.util.Constants.NEWS_DATABASE_NAME
import org.wit.allfootballapp.util.Constants.USERS_DATABASE_NAME
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FootballRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsRetrofit

@Module
@InstallIn(SingletonComponent::class)
object FootballModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-apisports-key", FOOTBALL_API_KEY)
                    .build()
                chain.proceed(request)
            })
            .build()
    }



    @Provides
    @Singleton
    @FootballRetrofit
    fun provideFootballRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FOOTBALL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideFootballApi(@FootballRetrofit retrofit: Retrofit): FootballApi {
        return retrofit.create(FootballApi::class.java)
    }




    @Provides
    @Singleton
    @NewsRetrofit
    fun provideNewsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL) // 你自己的新闻 API baseUrl
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(@NewsRetrofit retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCoachRepository(footballApi: FootballApi): CoachRepository{
        return CoachRepositoryImpl(footballApi)
    }

    @Provides
    @Singleton
    fun provideFixtureRepository(footballApi: FootballApi): FixtureRepository{
        return FixtureRepositoryImpl(footballApi)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(footballApi: FootballApi): PlayerRepository{
        return PlayerRepositoryImpl(footballApi)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(footballApi: FootballApi): TeamRepository{
        return TeamRepositoryImpl(footballApi)
    }

    @Provides
    @Singleton
    fun provideTransferRepository(footballApi: FootballApi): TransferRepository{
        return TransferRepositoryImpl(footballApi)
    }

    @Provides
    @Singleton
    fun providePlayerInfoRepository(footballApi: FootballApi): PlayerInfoRepository{
        return PlayerInfoRepositoryImpl(footballApi)
    }


    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideUserDao(
        usersDatabase: UsersDatabase
    ): UserDao = usersDatabase.userDao


    @Provides
    @Singleton
    fun provideUsersDatabase(
        application: Application
    ): UsersDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = UsersDatabase::class.java,
            name = USERS_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi,newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)


    @Provides
    @Singleton
    fun provideUsersRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

}
