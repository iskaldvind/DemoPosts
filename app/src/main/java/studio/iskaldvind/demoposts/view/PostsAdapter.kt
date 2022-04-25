package studio.iskaldvind.demoposts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import studio.iskaldvind.demoposts.R
import studio.iskaldvind.demoposts.databinding.PostItemBinding
import studio.iskaldvind.demoposts.model.Post

class PostsAdapter(
    val context: Context
) : PagingDataAdapter<Post, PostsViewHolder>(PostDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        PostsViewHolder(
            itemView = layoutInflater.inflate(R.layout.post_item, parent, false)
        )

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding: PostItemBinding by viewBinding(PostItemBinding::bind)

    fun bind(Post: Post?) {
        with(viewBinding) {
            title.text = Post?.title
            rating.text = (Post?.rating ?: 0).toString()
            comments.text = (Post?.comments ?: 0).toString()
        }
    }
}

private object PostDiffItemCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.title == newItem.title
}