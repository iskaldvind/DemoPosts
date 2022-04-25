package studio.iskaldvind.demoposts.repository.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import studio.iskaldvind.demoposts.model.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM posts WHERE name LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Post>

    @Query("DELETE FROM posts")
    suspend fun clearAll()
}