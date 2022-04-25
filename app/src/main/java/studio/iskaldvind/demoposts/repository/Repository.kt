package studio.iskaldvind.demoposts.repository

import studio.iskaldvind.demoposts.model.PostsDTO
import studio.iskaldvind.demoposts.repository.remote.IRepository
import studio.iskaldvind.demoposts.repository.remote.IRepositoryAPI

class Repository(
    private val api: IRepositoryAPI
) : IRepository {
    override suspend fun getPosts(after: String?, count: Int?): PostsDTO =
        api.fetchPostsAsync(after = after, count = count).await()
}