package security

import play.api.mvc.Session
import security.model.User


class AuthSessionService {

  def getSessionWithUser(session: Session, user: User) = {
    session.+(("userId", user.id.toString))
  }

}
