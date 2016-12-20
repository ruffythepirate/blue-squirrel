package controllers.api

import com.google.inject.Inject
import models.BlogPost
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, Controller}
import posts.EditBlogPostService

class PostsController @Inject() (blogPostService: EditBlogPostService) extends Controller {

  def post = Action {
    request =>
      request.body.asJson match{
        case blogPostJson: Some[JsValue] =>
          val blogPost = blogPostJson.get.as[BlogPost]
          val response = blogPostService.create(blogPost)
          Ok(Json.toJson(response))
        case None =>
          BadRequest("")
      }

  }
}
