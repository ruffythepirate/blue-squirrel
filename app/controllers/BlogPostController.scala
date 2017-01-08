package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import blogposts.ReadBlogPostService

import scala.util.{Failure, Success}

class BlogPostController @Inject() (readBlogPostService: ReadBlogPostService) extends Controller {

  def index(id: Long) = Action {
    val blogPostTry = readBlogPostService.getBlogPost(id)

    blogPostTry match {
      case Success(blogPost) =>
        Ok(views.html.posts.index(blogPost))
      case Failure(exception) =>
        InternalServerError("")
      case _ =>
        InternalServerError("")
    }
  }
}
