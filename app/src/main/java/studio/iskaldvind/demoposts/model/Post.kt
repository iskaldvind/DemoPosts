package studio.iskaldvind.demoposts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "comments") val comments: Int,
    @ColumnInfo(name = "name") val name: String,
)