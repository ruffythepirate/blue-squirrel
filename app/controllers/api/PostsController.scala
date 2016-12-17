package controllers.api

import models.BlogPost
import play.api.mvc.{Action, Controller}
import services.BlogPostService

class PostsController(blogPostService: BlogPostService) extends Controller {

  def post = Action {
    request =>
      val blogPostJson = request.body.asJson.get

      val blogPost = blogPostJson.as[BlogPost]

      val response = blogPostService.create(blogPost)

      Ok("")
  }
}
