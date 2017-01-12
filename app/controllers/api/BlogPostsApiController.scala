package controllers.api

import blogposts.BlogPostViewModel
import blogposts.services.EditBlogPostService
import com.google.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, Controller}

class BlogPostsApiController @Inject()(blogPostService: EditBlogPostService) extends Controller {

  def post = Action {
    request =>
      request.body.asJson match{
        case blogPostJson: Some[JsValue] =>
          val blogPost = blogPostJson.get.as[BlogPostViewModel]
          val response = blogPostService.create(blogPost)
          Ok(Json.toJson(response))
        case None =>
          BadRequest("")
      }

  }
}
