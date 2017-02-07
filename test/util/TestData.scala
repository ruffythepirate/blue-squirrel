package util

import blogposts.{BlogPost, BlogPostViewModel}
import org.joda.time.DateTime

trait TestData {

  val ANY_BLOGPOST_ID = 50

  val ANY_TIME = Some(new DateTime())

  val ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB = BlogPostViewModel(None, "Title", "Body", Seq.empty[String], None)

  val ANY_BLOGPOST_IN_DB = BlogPost(ANY_BLOGPOST_ID, "Title", "Body", ANY_TIME, ANY_TIME)
}
