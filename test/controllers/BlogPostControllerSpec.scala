package controllers

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatestplus.play.PlaySpec
import play.api.http.{HeaderNames, Status}
import play.api.test.{FakeRequest, ResultExtractors}
import posts.ReadBlogPostService
import util.TestData
import org.mockito.Mockito._

import scala.util.{Failure, Success}

class BlogPostControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData with ResultExtractors with HeaderNames with Status {

  implicit val timeout: Timeout = Timeout(3, TimeUnit.SECONDS)

  var cut: BlogPostController = _

  var readBlogPostService: ReadBlogPostService = _

  before {
    readBlogPostService = mock[ReadBlogPostService]

    cut = new BlogPostController(readBlogPostService)
  }

  "BlogPostController.index"  when {
    "blogpost exists" should {
      "return ok" in {
        when(readBlogPostService.getBlogPost(ANY_BLOGPOST_ID)).thenReturn(Success(ANY_BLOGPOST_IN_DB))
        val response = cut.index(ANY_BLOGPOST_ID).apply(FakeRequest())

        assert(status(response) === OK)
      }

      "get the blogpost from the BlogPostService" in {
        when(readBlogPostService.getBlogPost(ANY_BLOGPOST_ID)).thenReturn(Success(ANY_BLOGPOST_IN_DB))
        val response = cut.index(ANY_BLOGPOST_ID).apply(FakeRequest())

        verify(readBlogPostService).getBlogPost(ANY_BLOGPOST_ID)
      }
    }

    "there is a failure getting the blogpost" should {
      "return InternalServerError" in {
        when(readBlogPostService.getBlogPost(ANY_BLOGPOST_ID)).thenReturn(Failure(new Exception()))
        val response = cut.index(ANY_BLOGPOST_ID).apply(FakeRequest())

        assert(status(response) === INTERNAL_SERVER_ERROR)
      }
    }
  }
}
