package blogposts

import play.api.libs.json.Json
import org.joda.time.DateTime


case class BlogPostViewModel(id: Option[Long], title: String, body: String, tags: Seq[String], createdDate: Option[DateTime])

object BlogPostViewModel {
  implicit val blogPostFormat = Json.format[BlogPostViewModel]

  def apply(blogPost: BlogPost): BlogPostViewModel = {
    BlogPostViewModel(Some(blogPost.id), blogPost.title, blogPost.body, Seq.empty, blogPost.createdDate)
  }
}
