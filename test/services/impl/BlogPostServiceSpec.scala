package services.impl

import models.BlogPost
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import posts.impl.EditBlogPostService
import repositories.BlogPostRepository

class BlogPostServiceSpec extends PlaySpec with BeforeAndAfter with MockitoSugar{

  var cut: EditBlogPostService = _

  var blogPostRepository: BlogPostRepository = _

  val ANY_BLOGPOST = BlogPost(None, "title", "body")

  before {
    blogPostRepository = mock[BlogPostRepository]

    cut = new EditBlogPostService(blogPostRepository)
  }

  "BlogPostService.post" should {

    "call Repository.create with input" in {

      cut.create(ANY_BLOGPOST)

      verify(blogPostRepository).insert(ANY_BLOGPOST)
    }
  }

}
