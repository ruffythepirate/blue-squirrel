package controllers.api

import models.BlogPost
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
import posts.EditBlogPostService
import util.TestData

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
      "Calls postService with blogpost" in {
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
