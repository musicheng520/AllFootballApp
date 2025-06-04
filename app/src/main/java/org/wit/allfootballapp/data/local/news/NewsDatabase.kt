package org.wit.allfootballapp.data.local.news

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.wit.allfootballapp.domain.model.news.Article


@Database(entities = [Article::class], version = 2, exportSchema = false)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract val newsDao: NewsDao
}