package blogposts

import markdown.MarkdownService
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatestplus.play.PlaySpec
import repositories.BlogPostRepository
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import org.scalatest.mock.MockitoSugar
import util.TestData

class ReadBlogPostServiceSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData{

  var cut: ReadBlogPostService = _

  var blogPostRepository: BlogPostRepository= _
  var markdownService: MarkdownService = _

  before {
   blogPostRepository = mock[BlogPostRepository]
    markdownService = mock[MarkdownService]

    cut = new ReadBlogPostService(null, null)
  }

  "ReadBlogPostService.getBlogPost" when {

    "post doesn't exist" should {
      "not call markdown service" in {
        when(blogPostRepository.findById(ANY_BLOGPOST_ID)).thenReturn(None)

        verify(markdownService,never()).renderText(any())
      }

      "return a failure" in {
        when(blogPostRepository.findById(ANY_BLOGPOST_ID)).thenReturn(None)

      }
    }

    "post exists" should {

    }


  }

}
