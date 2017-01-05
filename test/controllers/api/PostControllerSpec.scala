package controllers.api

import markdown.MarkdownService
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._


class PostControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar{

    var cut : PostController = _

  var markdownService: MarkdownService = _

  val INPUT = "Test Markdown"
  val PARSED_MARKDOWN = "Parsed Markdown"

  before {
    markdownService = mock[MarkdownService]

    when(markdownService.renderText(INPUT)).thenReturn(PARSED_MARKDOWN)

    cut = new PostController(markdownService)
  }

  "PostController.parseMarkdown" should {
    "return the parsed markdown" in {
      val response = cut.parseMarkdown(INPUT).apply(FakeRequest())

      val responseText = contentAsString(response)
      assert(responseText === PARSED_MARKDOWN)
    }

    "uses the markdown service" in {
      val response = cut.parseMarkdown(INPUT).apply(FakeRequest())

      verify(markdownService).renderText(INPUT)
    }
  }

}
