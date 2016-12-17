package controllers.api

import models.BlogPost
import play.api.libs.json.JsValue
import play.api.mvc.{Action, Controller}
import services.BlogPostService

class PostsController(blogPostService: BlogPostService) extends Controller {

  def post = Action {
    request =>
      request.body.asJson match{
        case blogPostJson: Some[JsValue] =>
          val blogPost = blogPostJson.get.as[BlogPost]
          val response = blogPostService.create(blogPost)
          Ok("")
        case None =>
          BadRequest("")
      }

  }
}
