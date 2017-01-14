package blogposts

import org.joda.time.DateTime

import scala.language.postfixOps

case class BlogPost(id: Long, title: String, body: String, updatedDate: Option[DateTime], createdDate: Option[DateTime])

case class BlogPostTag(blogPostId: Long, tagId: Long)
