package models

import org.joda.time.DateTime
import play.api.libs.json.Json

import scala.language.postfixOps

case class BlogPost(id: Option[Long], title: String, body: String, updatedDate: Option[DateTime], createdDate: Option[DateTime])

object BlogPost {
  implicit val blogPostFormat = Json.format[BlogPost]
}

/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}


