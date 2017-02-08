package security

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.mockito.ArgumentMatchers._
import play.api.inject.ConstructionTarget
import play.api.{Configuration, Environment}
import play.api.test.FakeRequest
import scala.util.Success
import scala.util.Failure
import security.model.{MissingCredentialsException,InvalidCredentialsException}
import security.model.User
import org.mockito.Mockito._
import org.mockito.ArgumentMatchers._

class DefaultAuthServiceSpec extends PlaySpec with BeforeAndAfter with MockitoSugar{

  var cut: DefaultAuthService = _

  var conf: Configuration =_

  before{
    conf = mock[Configuration]
    when(conf.getString("auth.simple.username")).thenReturn(Some("admin"))
    when(conf.getString("auth.simple.password")).thenReturn(Some("correct"))

    cut = new DefaultAuthService(conf)
  }

  "DefaultAuthService.verifyCredentials" should {
    "accept correct credentials" in {
      val request = FakeRequest()
        .withFormUrlEncodedBody(("username", "admin"),
          ("password", "correct"))

        val response = cut.verifyCredentials(request)

        assert(response === Success(User("admin")))
    }

    "reject missing password" in {
      val request = FakeRequest()
        .withFormUrlEncodedBody(("username", "admin"))

        val response = cut.verifyCredentials(request)

        assert(response === Failure(MissingCredentialsException()))
    }

    "reject missing username" in {
      val request = FakeRequest()
        .withFormUrlEncodedBody(("password", "admin"))

        val response = cut.verifyCredentials(request)

        assert(response === Failure(MissingCredentialsException()))
    }

    "reject faulty username" in {
      val request = FakeRequest()
        .withFormUrlEncodedBody(("username", "wrong"),
          ("password", "correct"))

        val response = cut.verifyCredentials(request)

        assert(response === Failure(InvalidCredentialsException()))
    }


    "reject faulty password" in {
      val request = FakeRequest()
        .withFormUrlEncodedBody(("username", "admin"),
          ("password", "wrong"))

        val response = cut.verifyCredentials(request)

        assert(response === Failure(InvalidCredentialsException()))
    }
  }

}
