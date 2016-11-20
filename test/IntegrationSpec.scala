import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.specs2.specification.BeforeAfterAll
import play.api.test._
import play.api.test.Helpers._

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification with BeforeAfterAll {

  var driver : WebDriver = _

  override def beforeAll(): Unit = {
    driver =  new ChromeDriver()
  }

  override def afterAll(): Unit = {

  }

  "Application" should {

    "work from within a browser" in new WithBrowser(driver) {

      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Your new application is ready.")
    }
  }
}
