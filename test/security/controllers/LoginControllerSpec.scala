
package security.controllers

import org.mockito.Mockito._
import security.AuthService
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.test.FakeRequest
import play.api.test.Helpers._
import scala.util.{Success, Failure}
import security.model.User
import security.model.InvalidCredentialsException


class LoginControllerSpec extends PlaySpec with BeforeAndAfter with MockitoSugar{

  var cut : LoginController = _

  var authService: AuthService = _
  before {
    authService = mock[AuthService]
    cut = new LoginController(authService)
  }

  "LoginController.login" should {

    "return ok with auth cookie when correct credentials" in {
      val request = FakeRequest()
      when(authService.verifyCredentials(request)).thenReturn(Success(User("name")))

      val response = cut.login.apply(request)

      assert(status(response) === OK)
    }

    "return BadRequest without cookie on wrong credentials" in{
      val request = FakeRequest()
      when(authService.verifyCredentials(request)).thenReturn(Failure(InvalidCredentialsException()))

      val response = cut.login.apply(request)
      assert(status(response) === BAD_REQUEST)
    }
  }
}
