package repositories.impl

import org.scalatest.BeforeAndAfterAll
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.db.Database
import util.{DbMigrations, ItTestData}

class BlogPostRepositorySpec extends PlaySpec with BeforeAndAfterAll with MockitoSugar with ItTestData{

  var cut: BlogPostRepository = _

  var inMemoryDb: Database = _

  override def beforeAll() = {
    inMemoryDb =  DbMigrations.getMigratedDb()
    cut = new BlogPostRepository(inMemoryDb)
  }

  override def afterAll() = {
    DbMigrations.cleanUpDb()
  }

  "BlogPostRepository.update" should {
    "update the updated date of a blog post" in {
      val result = cut.insert(ANY_BLOGPOST_NOT_IN_DB)

      Thread.sleep(100)

      val updatedResult = cut.update(result.id.get, result.copy(body = "new body..."))

      val fromDb = cut.findById(result.id.get).get
      assert(fromDb.body === "new body...")
      assert(fromDb.updatedDate !== result.updatedDate)
      assert(fromDb.createdDate === result.createdDate)
    }
  }

  "BlogPostRepository.insert" should {
    "save a blogpost" in {
      val result = cut.insert(ANY_BLOGPOST_NOT_IN_DB)

      val fromDb = cut.findById(result.id.get).get
      assert(fromDb === result)
    }

    "return a blogpost with an id" in {
      val result = cut.insert(ANY_BLOGPOST_NOT_IN_DB)
      assert(result.id !== None)
    }

  }
}
