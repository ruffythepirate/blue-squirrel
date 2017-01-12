package blogposts.services

import blogposts.{BlogPost, BlogPostViewModel}
import blogposts.repositories.BlogPostRepository
import com.google.inject.Inject

class EditBlogPostService @Inject()(repository: BlogPostRepository) {
  def create(post: BlogPostViewModel): BlogPostViewModel = {
    val result = repository.insert(post)

    BlogPostViewModel(result)
  }
}
