package util

import models.BlogPost

trait TestData {

  val ANY_BLOGPOST = BlogPost(None, "Title", "Body")
}