package controllers

import org.scalatest.BeforeAndAfterAll
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.db.evolutions.Evolutions
import play.api.db.{Database, Databases}
import play.api.inject._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test._
import util.DbMigrations


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerSuite with BeforeAndAfterAll {

  // This is actually not necessary. Evolutions are done automatic when One App is generated.
  var inMemoryDb: Database = _

  lazy override val app = new GuiceApplicationBuilder()
      .overrides(bind[Database].toInstance(inMemoryDb))
      .build()

  override def beforeAll() = {
     inMemoryDb = DbMigrations.getMigratedDb()
  }

  override def afterAll() = {
    DbMigrations.cleanUpDb()
  }

  "BlogPostController.index"  when {
    "blogpost exists" should {
      "return ok" in {
        val res = route(app, FakeRequest(GET, "/posts/1")).get
        assert(status(res) === OK)
      }
    }
  }

  "Application" should {

    "send 404 on a bad request" in {
      val res = route(app, FakeRequest(GET, "/boum")).get
      assert(status(res) === NOT_FOUND)
    }

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      assert(status(home) === OK)
      assert(contentType(home) === Some("text/html"))
    }
  }
}
