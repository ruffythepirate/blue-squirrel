package tags

import org.scalatest.BeforeAndAfterAll
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import play.api.db.Database
import util.{DbMigrations, ItTestData}

class TagRepositorySpec extends PlaySpec with OneAppPerSuite with BeforeAndAfterAll with MockitoSugar with ItTestData {

  var cut: TagRepository = _

  var inMemoryDb: Database = _

  override def beforeAll() = {
    inMemoryDb = DbMigrations.getMigratedDb()
    cut = new TagRepository(inMemoryDb)
  }

  override def afterAll() = {
    DbMigrations.cleanUpDb()
  }

  "TagRepository.findByName" should {
    "return none if nothing found" in {
      val result = cut.findByName("Empty!")

      assert(result === None)
    }

    "return the tag if exists" in {

      cut.getOrInsert("exists")
      val result = cut.findByName("exists")

      assert(result.get.name === "exists")
    }
  }

  "TagRepository.findAll" should {
    "return empty sequence if no entries" in {
      inMemoryDb.withConnection(c => {
        c.nativeSQL("delete from tags")
        c.commit()
      })
      val all = cut.findAll()
      all.foreach(e => cut.delete(e.id))

      val result = cut.findAll()

      assert(result === List.empty)
    }

    "return all entries" in {
      inMemoryDb.withConnection(c => {
        c.nativeSQL("delete from tags")
        c.commit()
      })
//      inMemoryDb.withConnection(c => {
//        c.nativeSQL("insert into tags (name) values ('hello1')")
//        c.nativeSQL("insert into tags (name) values ('hello2')")
//        c.nativeSQL("insert into tags (name) values ('hello3')")
//        c.commit()
//      })

      cut.getOrInsert("exists1")
      cut.getOrInsert("exists2")
      cut.getOrInsert("exists3")


      val result = cut.findAll()
      assert(result.size === 3)
    }
  }

  "TagRepository.delete" should {
    "do nothing if nothing to delete" in {
      val firstEntry = cut.getOrInsert("exists1")

      val result = cut.delete(firstEntry.id + 1)

      assert(firstEntry.id === cut.getOrInsert("exists1").id)
    }

    "delete the entry if exists" in {
      val firstEntry = cut.getOrInsert("exists1")

      val result = cut.delete(firstEntry.id)

      assert(firstEntry.id !== cut.getOrInsert("exists1").id)
    }
  }

  "TagRepository.getOrInsert" should {
    "create entry only once" in {
      val firstEntry = cut.getOrInsert("new!")
      val sameEntry = cut.getOrInsert("new!")

      assert(firstEntry === sameEntry)
    }

    "save an entry" in {
      val firstEntry = cut.getOrInsert("new!")
      val sameEntry = cut.findByName("new!").get

      assert(firstEntry === sameEntry)
    }
  }
}
