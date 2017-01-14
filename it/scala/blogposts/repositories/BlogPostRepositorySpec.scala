package blogposts.repositories

import org.scalatest.BeforeAndAfterAll
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.db.Database
import tags.TagRepository
import tags.models.Tag
import util.{DbMigrations, ItTestData}

class BlogPostRepositorySpec extends PlaySpec with BeforeAndAfterAll with MockitoSugar with ItTestData{

  var cut: BlogPostRepository = _

  var inMemoryDb: Database = _

  var databaseTags: Seq[Tag]= _

  override def beforeAll() = {
    inMemoryDb =  DbMigrations.getMigratedDb()
    cut = new BlogPostRepository(inMemoryDb)

    val tagRepo = new TagRepository(inMemoryDb)

    databaseTags = Seq("1", "2").map(tagRepo.getOrInsert(_))
  }

  override def afterAll() = {
    DbMigrations.cleanUpDb()
  }

  "BlogPostRepository.update" should {
    "update the updated date of a blog post" in {
      val result = cut.insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)

      Thread.sleep(100)

      val updatedResult = cut.update(result.id, result.copy(body = "new body..."))

      val fromDb = cut.findById(result.id).get
      assert(fromDb.body === "new body...")
      assert(fromDb.updatedDate !== result.updatedDate)
      assert(fromDb.createdDate === result.createdDate)
    }
  }

  "BlogPostRepository.insert" should {
    "save a blogpost" in {
      val result = cut.insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)

      val fromDb = cut.findById(result.id).get
      assert(fromDb === result)
    }

    "save a blogpost with tags" in {
      val result = cut.insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB, databaseTags.map(_.id))

      val fromDb = cut.getTagIdsForBlogpost(result.id)

      assert(fromDb.map(_.tagId).sorted === databaseTags.map(_.id).sorted)

    }

    "return a blogpost with an id" in {
      val result = cut.insert(ANY_BLOGPOSTVIEWMODEL_NOT_IN_DB)
      assert(result.id !== None)
    }

  }
}
