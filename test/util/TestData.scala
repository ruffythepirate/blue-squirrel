package util

import models.BlogPost

trait TestData {

  val ANY_BLOGPOST_ID = 50

  val ANY_BLOGPOST_NOT_IN_DB = BlogPost(None, "Title", "Body")
  val ANY_BLOGPOST_IN_DB = BlogPost(Some(ANY_BLOGPOST_ID), "Title", "Body")
}
