package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import posts.EditBlogPostService
import repositories.BlogPostRepository

class IndexController @Inject() (blogPostRepository: BlogPostRepository) extends Controller {

  def index = Action {
    val blogPosts = blogPostRepository.list(0, 10, 1, "%")
    Ok(views.html.index.index(blogPosts))
  }

}
