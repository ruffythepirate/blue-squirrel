import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerSuite {

  "Application" should {

    "send 404 on a bad request" in {
      val res = route(app, FakeRequest(GET, "/boum")).get
      assert(status(res) === NOT_FOUND)
    }

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      assert(status(home) === OK)
      assert(contentType(home) === Some("text/html"))
      assert(contentAsString(home).contains("Your new application is ready."))
    }
  }
}
