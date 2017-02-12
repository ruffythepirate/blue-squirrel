package security

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.mockito.ArgumentMatchers._
import play.api.inject.ConstructionTarget
import play.api.{Configuration, Environment}
import play.api.test.FakeRequest
import play.api.mvc.Session
import scala.util.Success
import scala.util.Failure
import security.model.{MissingCredentialsException,InvalidCredentialsException}
import security.model.User
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._
import util.TestData

class AuthSessionServiceSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData{

  var cut: AuthSessionService = _

  before{
    cut = new AuthSessionService
  }

  "AuthSessionService.getUserToSession" should {
    "add userid to session" in {

      val newSession = cut.getSessionWithUser(Session(Map[String,String]()), ANY_USER)

      assert( newSession("userId") === ANY_USER.id.toString)
    }

  }

}
