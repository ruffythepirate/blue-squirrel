package services.impl
import com.google.inject.Inject
import models.BlogPost
import repositories.BlogPostRepository

class BlogPostService @Inject() ( repository: BlogPostRepository) extends services.BlogPostService{
  override def create(post: BlogPost): BlogPost = {
    repository.insert(post)
  }
}
