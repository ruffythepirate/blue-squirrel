package security

import play.api.mvc.{AnyContent, Request}
import security.model.User
import scala.util.Try

trait AuthService {
  def verifyCredentials(request: Request[AnyContent]) : Try[User]
}
