package controllers.api

import com.google.inject.Inject
import markdown.MarkdownService
import play.api.mvc.{Action, Controller}

class PostController @Inject() (markdownService: MarkdownService) extends Controller {

  def parseMarkdown(markdown: String) = Action {
    request =>
      val result = markdownService.renderText(markdown)
      Ok(result)
  }
}
