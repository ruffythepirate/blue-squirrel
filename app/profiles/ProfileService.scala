package profiles

import profiles.model._

class ProfileService() {

  def getProfile: Profile = {

    Profile(
      Person("Johan", ""),
      Position("Fullstack Developer", "IS24", ""),
      Seq.empty[SocialLink])
  }
}
