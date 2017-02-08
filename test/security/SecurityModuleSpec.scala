package security

import org.scalatest.BeforeAndAfter
import org.scalatestplus.play.PlaySpec
import org.scalatest.mock.MockitoSugar
import org.mockito.ArgumentMatchers._
import play.api.inject.ConstructionTarget
import play.api.{Configuration, Environment}

class SecurityModuleSpec extends PlaySpec with BeforeAndAfter with MockitoSugar{

  var cut: SecurityModule = _

  var conf: Configuration =_
  var env: Environment = _

  before{
    cut = new SecurityModule()
    conf = mock[Configuration]
    env = mock[Environment]
  }
  "SecurityModule.bindings" should {
    "return the default auth service" when {
      "config auth.type is set to default" in {
       val result = cut.bindings(env, conf)

        //This row verifies that it resolves the auth service to default auth service.
        val binding = result(0).target.get.asInstanceOf[ConstructionTarget[DefaultAuthService]]
      }
    }

  }
}
