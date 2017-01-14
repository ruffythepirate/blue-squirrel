package tags.controllers

import com.google.inject.Inject
import play.api.libs.json.{JsArray, Json}
import play.api.mvc.{Action, Controller}
import tags.TagService
import tags.TagViewModel

class TagsApiController @Inject() (tagService: TagService) extends Controller {

  def getAll = Action {
    val result = tagService.getAll()

    Ok(Json.toJson(result))
  }
}
