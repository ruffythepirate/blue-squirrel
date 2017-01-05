package util

import models.BlogPost
import org.joda.time.DateTime

trait TestData {

  val ANY_BLOGPOST_ID = 50

  val ANY_TIME = Some(new DateTime())
  val ANY_BLOGPOST_NOT_IN_DB = BlogPost(None, "Title", "Body", None, None)
  val ANY_BLOGPOST_IN_DB = BlogPost(Some(ANY_BLOGPOST_ID), "Title", "Body", ANY_TIME, ANY_TIME)
}
