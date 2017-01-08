package blogposts

import com.google.inject.Inject
import markdown.MarkdownService
import models.BlogPost
import repositories.BlogPostRepository

import scala.util.{Failure, Success, Try}

class ReadBlogPostService @Inject() (markdownService: MarkdownService, repo: BlogPostRepository) {

  def getBlogPost(id: Long): Try[BlogPost] = {
    repo.findById(id) match {
      case Some(blogPost) =>
        val bodyContent = markdownService.renderText(blogPost.body)

        Success(blogPost.copy(body = bodyContent))
      case None =>
        Failure(PostNotFoundException())
    }
  }
}
