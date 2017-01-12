package blogposts

import blogposts.repositories.BlogPostRepository
import blogposts.services.EditBlogPostService
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import tags.TagService
import util.TestData

class EditBlogPostServiceSpec extends PlaySpec with BeforeAndAfter with TestData with MockitoSugar{

  var cut: EditBlogPostService = _

  var blogPostRepository: BlogPostRepository = _
  var tagService: TagService = _

  before {
    blogPostRepository = mock[BlogPostRepository]
    tagService = mock[TagService]

    when(blogPostRepository.insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)).thenReturn(ANY_BLOGPOST_IN_DB)

    cut = new EditBlogPostService(blogPostRepository, tagService)
  }

  "BlogPostService.post" should {

    "call Repository.create with input" in {
      cut.create(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)

      verify(blogPostRepository).insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)
    }
  }

}
