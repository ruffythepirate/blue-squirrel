package tags.controllers

import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfter, FunSuite}
import org.scalatestplus.play.PlaySpec
import tags.{TagService, TagViewModel}
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import play.api.test.FakeRequest

class TagsApiControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar {

  var cut: TagsApiController = _

  var tagServiceMock: TagService = _

  before {
    tagServiceMock = mock[TagService]

    cut = new TagsApiController(tagServiceMock)

    when(tagServiceMock.getAll()).thenReturn(Seq(TagViewModel("hello", Some(DateTime.now))))
  }

 "testGetAll" should {

    "call findAll from Service" in {
      cut.getAll.apply(FakeRequest())

      verify(tagServiceMock).getAll()
    }

  }

}
