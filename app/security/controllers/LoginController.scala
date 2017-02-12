package security.controllers

import play.api.mvc.{Action, Controller}
import com.google.inject.Inject
import security.{AuthService, AuthSessionService}
import scala.util.{Success, Failure}

class LoginController @Inject() (authService: AuthService, authSessionService: AuthSessionService) extends Controller {
  def index = Action {
    Ok(views.html.user.login.login())
  }

  def login = Action { request =>
    val response = authService.verifyCredentials(request)

    response match {
      case Success(user) =>

        Ok("")
          .withSession(authSessionService.getSessionWithUser(request.session, user))
      case Failure(exc) =>
        BadRequest("")
    }
  }
}
