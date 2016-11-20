import org.specs2.mutable._
import org.specs2.specification.BeforeAll
import play.api.{Application, Mode}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification with BeforeAll {

//  val fakeApplication = new FakeApplication()
  var fakeApplication: Application = _

  override def beforeAll(): Unit = {
   fakeApplication = new GuiceApplicationBuilder()
//      .in(new File("path/to/app"))
      .in(Mode.Test)
//      .in(classLoader)
      .build
  }

  "Application" should {

    "send 404 on a bad request" in {//in new WithApplication(fakeApplication){
      route(fakeApplication, FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the index page" in { //new WithApplication(fakeApplication){
      val home = route(fakeApplication, FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }
  }
}
