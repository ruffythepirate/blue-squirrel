package blogposts.impl

import com.google.inject.Inject
import models.BlogPost
import repositories.BlogPostRepository

class EditBlogPostService @Inject()(repository: BlogPostRepository) extends blogposts.EditBlogPostService{
  override def create(post: BlogPost): BlogPost = {
    repository.insert(post)
  }
}
