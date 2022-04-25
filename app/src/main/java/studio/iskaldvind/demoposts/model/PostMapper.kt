package studio.iskaldvind.demoposts.model

fun postMapper(postData: PostData): Post =
    Post(
        id = 0,
        title = postData.title,
        rating = postData.score,
        comments = postData.comments,
        name = postData.name
    )