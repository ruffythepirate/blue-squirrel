package tags

import org.joda.time.DateTime
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import tags.models.Tag

class TagServiceTest extends PlaySpec with BeforeAndAfter with MockitoSugar{

  var cut: TagService = _

  var tagRepositoryMock: TagRepository = _

  val TEST_TAGS = Seq("First", "Second", "Third", "ETC").map(text => Tag(currentIndex, text, DateTime.now))

  var currentIndex = 0l
  def getIndex = {
    currentIndex += 1
    currentIndex
  }

  before {
    tagRepositoryMock = mock[TagRepository]

    TEST_TAGS.foreach(tag => when(tagRepositoryMock.getOrInsert(tag.name)).thenReturn(tag))
    cut = new TagService(tagRepositoryMock)

    when(tagRepositoryMock.findAll()).thenReturn(List(Tag(3l, "hello", DateTime.now)))
  }

  "getOrCreateTagIds" should {

    "return ids for each tag" in {

      val result = cut.getOrCreateTagIds(TEST_TAGS.map(_.name))

      assert( result === TEST_TAGS.map(_.id))
    }
  }

  "getAll" should {
    "get all from repository" in {
      cut.getAll()

      verify(tagRepositoryMock).findAll()
    }
  }

}
