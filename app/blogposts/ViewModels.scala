package blogposts

import play.api.libs.json._
import org.joda.time.DateTime


case class BlogPostViewModel(id: Option[Long], title: String, body: String, tags: Seq[String], createdDate: Option[DateTime])

object BlogPostViewModel {
  implicit val blogPostFormat = Json.format[BlogPostViewModel]

    implicit val yourJodaDateReads = Reads.jodaDateReads("yyyy-MM-dd'T'HH:mm:ss'Z'")
    implicit val yourJodaDateWrites = Writes.jodaDateWrites("yyyy-MM-dd'T'HH:mm:ss'Z'")


  def apply(blogPost: BlogPost): BlogPostViewModel = {
    BlogPostViewModel(Some(blogPost.id), blogPost.title, blogPost.body, Seq.empty, blogPost.createdDate)
  }
}
