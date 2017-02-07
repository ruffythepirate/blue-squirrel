package controllers

import blogposts.services.ReadBlogPostService
import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

import scala.util.{Failure, Success}
import profiles.ProfileService

class BlogPostController @Inject() (profileService: ProfileService, readBlogPostService: ReadBlogPostService) extends Controller {

  def index(id: Long) = Action {
    val blogPostTry = readBlogPostService.getBlogPost(id)

    val profile = profileService.getProfile

    blogPostTry match {
      case Success(blogPost) =>
        Ok(views.html.posts.index(profile, blogPost))
      case Failure(exception) =>
        InternalServerError("")
      case _ =>
        InternalServerError("")
    }
  }
}
