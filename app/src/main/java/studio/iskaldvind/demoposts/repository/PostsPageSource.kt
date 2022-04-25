package studio.iskaldvind.demoposts.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import studio.iskaldvind.demoposts.model.Post
import studio.iskaldvind.demoposts.model.postMapper
import studio.iskaldvind.demoposts.repository.remote.IRepository

class PostsPageSource(
    private val repository: IRepository
) : PagingSource<Int, Post>() {

    private var after: String? = null
    private var count = 0

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val pageIndex: Int = params.key ?: 1
        return try {
            val qCount = if (count == 0) null else count
            val response = repository.getPosts(after = after, count = qCount)
            val responseData = response.data
            after = responseData.after
            val children = responseData.children
            count = children.size
            val nextKey = if (response.data.children.isEmpty()) null else pageIndex + 1
            val prevKey = if (pageIndex == 1) null else pageIndex
            val results = children.map { postMapper(it.data) }
            LoadResult.Page(results, prevKey, nextKey)
        } catch (e: RuntimeException) {
            LoadResult.Error(e)
        }
    }
}