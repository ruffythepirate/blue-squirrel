package repositories.impl

import org.scalatest.BeforeAndAfterAll
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.db.Databases
import play.api.db.evolutions.Evolutions
import util.ItTestData

class BlogPostRepositorySpec extends PlaySpec with BeforeAndAfterAll with MockitoSugar with ItTestData{

  var cut: BlogPostRepository = _

  val inMemoryDb = Databases.inMemory()

  override def beforeAll() = {
    Evolutions.applyEvolutions(inMemoryDb)

    cut = new BlogPostRepository(inMemoryDb)
  }

  override def afterAll() = {
    Evolutions.cleanupEvolutions(inMemoryDb)
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
