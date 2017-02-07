package profiles.model

case class Profile(person: Person, currentPosition: Position, socialLinks: Seq[SocialLink])

case class Person(name: String, imageUrl:String = "", shortBiography: String = "")

case class Position(title: String, company: String, url: String)

case class SocialLink(url: String, image: String, title: String = "")
