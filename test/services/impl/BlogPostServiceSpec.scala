package services.impl

import models.BlogPost
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import posts.impl.EditBlogPostService
import repositories.BlogPostRepository
import util.TestData

class BlogPostServiceSpec extends PlaySpec with BeforeAndAfter with TestData with MockitoSugar{

  var cut: EditBlogPostService = _

  var blogPostRepository: BlogPostRepository = _

  before {
    blogPostRepository = mock[BlogPostRepository]

    cut = new EditBlogPostService(blogPostRepository)
  }

  "BlogPostService.post" should {

    "call Repository.create with input" in {

      cut.create(ANY_BLOGPOST_NOT_IN_DB)

      verify(blogPostRepository).insert(ANY_BLOGPOST_NOT_IN_DB)
    }
  }

}
