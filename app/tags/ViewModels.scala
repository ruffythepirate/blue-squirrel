package tags

import org.joda.time.DateTime
import play.api.libs.json.Json
import tags.models.Tag

case class TagViewModel(name: String, createDate: Option[DateTime])

object TagViewModel {

  implicit val format = Json.format[TagViewModel]

  def apply(tag: Tag): TagViewModel = {
    TagViewModel(tag.name, Some(tag.createdDate))
  }

}
