package blogposts.services

import blogposts.repositories.BlogPostRepository
import blogposts.{BlogPost, BlogPostViewModel, PostNotFoundException}
import com.google.inject.Inject
import common.Page
import markdown.MarkdownService

import scala.util.{Failure, Success, Try}

class ReadBlogPostService @Inject() (markdownService: MarkdownService, repo: BlogPostRepository) {

  def getBlogPost(id: Long): Try[BlogPostViewModel] = {
    repo.findById(id) match {
      case Some(blogPost) =>
        val vm = BlogPostViewModel(blogPost)
        val bodyContent = markdownService.renderText(vm.body)

        Success(vm.copy(body = bodyContent))
      case None =>
        Failure(PostNotFoundException())
    }
  }

  def list(page: Int, pageSize: Int, orderBy: Int, filter:String): Page[BlogPostViewModel] = {
    val result = repo.list(page, pageSize, orderBy, filter)

    Page(result.items.map(BlogPostViewModel(_)), result.page, result.offset, result.offset)
  }
}
