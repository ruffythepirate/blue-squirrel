import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.BeforeAndAfterAll
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.test.WithBrowser

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
class IntegrationSpec extends PlaySpec with BeforeAndAfterAll with OneAppPerSuite {

  var driver : WebDriver = _

  override def beforeAll(): Unit = {
    driver =  new ChromeDriver()
  }

  "Application" should {

    "work from within a browser" in new WithBrowser() {
      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Your new application is ready.")
    }
  }
}
