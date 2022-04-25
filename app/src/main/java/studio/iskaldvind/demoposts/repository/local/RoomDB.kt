package studio.iskaldvind.demoposts.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import studio.iskaldvind.demoposts.model.Post

@Database(entities = [Post::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun postDao(): PostsDao
}