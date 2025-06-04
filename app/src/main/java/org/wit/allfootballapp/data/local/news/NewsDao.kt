package org.wit.allfootballapp.data.local.news

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.news.Article


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>


    @Query("SELECT * FROM Article where url=:url")
    suspend  fun getArticle(url: String): Article?

}