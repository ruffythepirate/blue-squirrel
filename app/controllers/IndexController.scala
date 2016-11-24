package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import repositories.BlogPostRepository
import services.BlogPostService

class IndexController @Inject() (blogPostRepository: BlogPostRepository) extends Controller {

  def index = Action {

    val blogPosts = blogPostRepository.list(0, 10, 1, "%")
    Ok(views.html.index(blogPosts))

  }

}