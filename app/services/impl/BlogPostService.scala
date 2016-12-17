package services.impl
import models.BlogPost
import repositories.BlogPostRepository

class BlogPostService( repository: BlogPostRepository) extends services.BlogPostService{
  override def create(post: BlogPost): BlogPost = {
    repository.insert(post)
  }
}
