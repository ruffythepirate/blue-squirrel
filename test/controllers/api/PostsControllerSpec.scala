package controllers.api

import java.util.concurrent.TimeUnit

import akka.util.Timeout
import models.BlogPost
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsObject, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.mockito.Mockito._
import org.scalatest.tools.Durations
import posts.EditBlogPostService
import util.TestData

import scala.concurrent.duration.{Duration, FiniteDuration}

class PostsControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData {

  var cut: PostsController = _

  var postService: EditBlogPostService = _

  before {
    postService = mock[EditBlogPostService]

    when(postService.create(ANY_BLOGPOST_NOT_IN_DB)).thenReturn(ANY_BLOGPOST_NOT_IN_DB)

    cut = new PostsController(postService)

  }

  "PostsController.post" when {
    "request is valid" should {
      "return OK" in {
        val request = createPostRequest()
        cut.post.apply(request)

        verify(postService).create(ANY_BLOGPOST_NOT_IN_DB)
      }

      "call BlogPostService" in {
        val request = createPostRequest()
        val response = cut.post.apply(request)

        assert(status(response) === OK)
      }

      "return a BlogPost as Json" in {

        val request = createPostRequest()
        val response = cut.post.apply(request)
        val responseBody = contentAsJson(response)
        val resultBlogPost = responseBody.as[BlogPost]

        assert(resultBlogPost === ANY_BLOGPOST_NOT_IN_DB)
      }
    }

    "request is invalid" should {
      "return BAD_REQUEST" in {
        val invalidRequest = FakeRequest()
        val response = cut.post.apply(invalidRequest)

        assert(status(response) === BAD_REQUEST)
      }
    }
  }

  def createPostRequest() = {
    FakeRequest().withJsonBody(
      Json.obj(
        "title" -> ANY_BLOGPOST_NOT_IN_DB.title,
        "body" -> ANY_BLOGPOST_NOT_IN_DB.body
      )
    )
  }
}
