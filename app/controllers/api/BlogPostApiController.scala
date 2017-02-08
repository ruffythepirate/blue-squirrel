package controllers.api

import com.google.inject.Inject
import markdown.MarkdownService
import play.api.mvc.{Action, AnyContent, Controller, Request}

class BlogPostApiController @Inject()(markdownService: MarkdownService) extends Controller {

  def parseMarkdown(markdown: String) = Action {
    request: Request[AnyContent] =>
      val result = markdownService.renderText(markdown)
      Ok(result)
  }
}
