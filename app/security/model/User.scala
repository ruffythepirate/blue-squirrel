package security.model

case class User(id: Long, name: String, isAdmin: Boolean = false)
