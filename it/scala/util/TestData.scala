package util

import models.BlogPost

trait ItTestData {

  val ANY_BLOGPOST = BlogPost(None, "Title", "Body")

}
