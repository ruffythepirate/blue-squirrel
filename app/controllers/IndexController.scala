package controllers

import blogposts.repositories.BlogPostRepository
import blogposts.services.ReadBlogPostService
import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

class IndexController @Inject() (readBlogPostService: ReadBlogPostService) extends Controller {

  def index = Action {

    val blogPosts = readBlogPostService.list(0, 10, 1, "%")
    Ok(views.html.index.index(blogPosts))
  }

}
