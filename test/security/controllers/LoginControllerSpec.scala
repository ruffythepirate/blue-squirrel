
package security.controllers

import org.mockito.Mockito._
import security.{AuthService,AuthSessionService}
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._
import scala.util.{Success, Failure}
import security.model.User
import security.model.InvalidCredentialsException
import util.TestData


class LoginControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar with TestData {

  var cut : LoginController = _

  var authService: AuthService = _
  var authSessionService: AuthSessionService = _
  before {
    authService = mock[AuthService]
    authSessionService = mock[AuthSessionService]
    cut = new LoginController(authService, authSessionService)
  }

  "LoginController.login" should {

    "return ok with auth cookie when correct credentials" in {
      val request = FakeRequest()
      when(authService.verifyCredentials(request)).thenReturn(Success(ANY_USER))
      when(authSessionService.getSessionWithUser(request.session, ANY_USER)).thenReturn(request.session)

      val response = cut.login.apply(request)

      assert(status(response) === OK)
      assert(header("set-cookie", response) !== None)
    }

    "return BadRequest without cookie on wrong credentials" in{
      val request = FakeRequest()
      when(authService.verifyCredentials(request)).thenReturn(Failure(InvalidCredentialsException()))

      val response = cut.login.apply(request)
      assert(status(response) === BAD_REQUEST)
      assert(header("set-cookie", response) !== None)
    }
  }
}
