package security

import play.api. {Environment, Configuration}
import play.api.inject.Module


class SecurityModule extends Module {
  def bindings(env:Environment, conf: Configuration) = {

    Seq(
      bind[AuthService].to[DefaultAuthService]
      )
  }

}
