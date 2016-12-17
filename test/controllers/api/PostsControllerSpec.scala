package controllers.api

import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.{JsObject, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.BlogPostService
import org.mockito.Mockito._
import util.TestData

class PostsControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData {

  var cut: PostsController = _

  var postService: BlogPostService = _

  before {
    postService = mock[BlogPostService]

    cut = new PostsController(postService)
  }

  "PostsController.post" when {
    "request is valid" should {
      "return OK" in {
        val request = createPostRequest()
        val response = cut.post.apply(request)

        verify(postService).create(ANY_BLOGPOST)
      }

      "call BlogPostService" in {
        val request = createPostRequest()
        val response = cut.post.apply(request)

        assert(status(response) === OK)
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
        "title" -> ANY_BLOGPOST.title,
        "body" -> ANY_BLOGPOST.body
      )
    )
  }
}
