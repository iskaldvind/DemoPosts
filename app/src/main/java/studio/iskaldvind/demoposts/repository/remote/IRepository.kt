package studio.iskaldvind.demoposts.repository.remote

import studio.iskaldvind.demoposts.model.PostsDTO

interface IRepository {
    suspend fun getPosts(after: String?, count: Int?): PostsDTO
}