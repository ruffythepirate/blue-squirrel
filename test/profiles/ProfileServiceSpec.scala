package profiles

import org.scalatestplus.play.PlaySpec
import org.scalatest.BeforeAndAfter

class ProfileServiceSpec extends PlaySpec with BeforeAndAfter {

var cut: ProfileService = _

before {
  cut = new ProfileService()
}

  "ProfileService.getProfile" should {

    "Always return the same result" in {
      val result1 = cut.getProfile
      val result2 = cut.getProfile

      assert(result1 === result2)

    }
  }

}
