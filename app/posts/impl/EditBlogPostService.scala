package posts.impl

import com.google.inject.Inject
import models.BlogPost
import repositories.BlogPostRepository

class EditBlogPostService @Inject()(repository: BlogPostRepository) extends posts.EditBlogPostService{
  override def create(post: BlogPost): BlogPost = {
    repository.insert(post)
  }
}

