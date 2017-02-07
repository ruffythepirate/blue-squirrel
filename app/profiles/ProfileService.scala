package profiles

import profiles.model._

class ProfileService() {

  def getProfile: Profile = {

    Profile(
      Person("Johan", "http://www.aspirehire.co.uk/aspirehire-co-uk/_img/profile.svg"),
      Position("Fullstack Developer", "IS24", ""),
      Seq.empty[SocialLink])
  }
}
