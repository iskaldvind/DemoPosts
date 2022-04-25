package studio.iskaldvind.demoposts.model

import com.google.gson.annotations.SerializedName

data class PostsDTO (
    @SerializedName("data") val data: Data
)

data class Data(
    @SerializedName("after") val after: String?,
    @SerializedName("children") val children: List<Child>,
)

data class Child(
    @SerializedName("data") val data: PostData
)

data class PostData(
    @SerializedName("title") val title: String,
    @SerializedName("score") val score: Int,
    @SerializedName("name") val name: String,
    @SerializedName("num_comments") val comments: Int,
)