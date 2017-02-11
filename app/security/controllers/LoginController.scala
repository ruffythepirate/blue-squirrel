package security.controllers

import play.api.mvc.{Action, Controller}
import com.google.inject.Inject
import security.AuthService

class LoginController @Inject() (authService: AuthService) extends Controller {
  def index = Action {
    Ok(views.html.user.login.login())
  }
}
