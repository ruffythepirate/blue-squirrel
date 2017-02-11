package security.controllers

import play.api.mvc.{Action, Controller}
import com.google.inject.Inject
import security.AuthService
import scala.util.{Success, Failure}

class LoginController @Inject() (authService: AuthService) extends Controller {
  def index = Action {
    Ok(views.html.user.login.login())
  }

  def login = Action { request =>
    val response = authService.verifyCredentials(request)

    response match {
      case Success(user) =>
        Ok("")
      case Failure(exc) =>
        BadRequest("")
    }
  }
}
