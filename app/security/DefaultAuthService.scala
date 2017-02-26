package security

import play.api.mvc.{AnyContent, Request}

import scala.util.Try
import scala.util.Success
import security.model.User
import play.api.Configuration
import com.google.inject.Inject
import play.api.libs.json.{JsObject, JsString, JsValue}
import security.model.{InvalidCredentialsException, MissingCredentialsException}

import scala.util.Failure
import play.mvc.Http._

class DefaultAuthService @Inject()(conf: Configuration) extends AuthService {

  val correctUsername = conf.getString("auth.simple.username")
  val correctPassword = conf.getString("auth.simple.password")

  def verifyCredentials(request: Request[AnyContent]): Try[User] = {

    for {
      username <- getUsername(request)
      password <- getPassword(request)
      user <- getUser(username, password)
    } yield user
  }

  private def getUsername(request: Request[AnyContent]) =
    getCredentialParameter(request, "username")

  private def getCredentialParameter(request: Request[AnyContent], name:String) = {
    val parameters = request.body.asJson.getOrElse(JsObject(Seq()))

      parameters.\(name).toOption match {
        case Some(JsString(value)) => Success(value)
        case _ => Failure(MissingCredentialsException())
      }
  }

  private def getPassword(request: Request[AnyContent]) =
    getCredentialParameter(request, "password")

  private def getUser(username: String, password: String) = {
    if(correctPassword != None && username != None) {
      if(username == correctUsername.get && password == correctPassword.get)
        Success(User(1l, username, false))
      else
        Failure(InvalidCredentialsException())
    }
    else
      Failure(InvalidCredentialsException())
  }

}
