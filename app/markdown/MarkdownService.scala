package markdown

import org.commonmark.html.HtmlRenderer
import org.commonmark.node.Node
import org.commonmark.parser.Parser

class MarkdownService {

  val parser = Parser.builder().build()
  val renderer = HtmlRenderer.builder().build()


  def renderText(markdownContent: String): String = {
    val document = parser.parse(markdownContent)
    renderer.render(document)
  }
}
