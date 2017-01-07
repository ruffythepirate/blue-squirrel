package tags

import anorm.SqlParser._
import anorm.{~, _}
import com.google.inject.Inject
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.Database
import tags.models.Tag

class TagRepository @Inject()(db: Database) {

  // -- Parsers

  /**
    * Parse a Tag from a ResultSet
    */
  private val tag = {
    get[Long]("tags.id") ~
      get[String]("tags.name") ~
      get[DateTime]("tags.created_date") map {
      case id ~ name ~ createdDate => Tag(id, name, createdDate)
    }
  }

  // -- Queries
  /**
    * Retrieve a tag from the id.
    */
  private def findById(id: Long): Option[Tag] = {
    db.withConnection { implicit connection =>
      SQL("select * from tags where id = {id}").on('id -> id)
        .as(tag.singleOpt)
    }
  }

  /**
    * Retrieve a tag from the id.
    */
  def findByName(name: String): Option[Tag] = {
    db.withConnection { implicit connection =>
      SQL("select * from tags where name = {name}").on('name -> name)
        .as(tag.singleOpt)
    }
  }

  /**
    * Retrieve all blogPost.
    *
    * @return
    */
  def findAll(): List[Tag] = {
    db.withConnection { implicit connection =>
      try {
        SQL("select * from tags order by name").as(tag *)
      } catch {
        case ex: Exception => Logger.info("ERROR", ex); Nil
      }
    }
  }

  /**
    * Insert a new blogPost.
    *
    * @param tagName The employee values.
    */
  def getOrInsert(tagName: String): Tag = {

     findByName(tagName) match {
      case Some(tag) => tag
      case None => insert(tagName)
    }
  }

  private def insert(name: String): Tag = {
    val id = db.withConnection { implicit connection =>
      SQL("""insert into tags (id, name, created_date) values ({id}, {name}, NOW())""")
        .on(
          'id -> Option.empty[Long],
          'name -> name)
        .executeInsert()
    }
    findById(id.get).get
  }

  /**
    * Delete a tag.
    *
    * @param id Id of the tag to delete.
    */
  def delete(id: Long): Int = {
    db.withConnection { implicit connection =>
      SQL("delete from tags where id = {id}").on('id -> id).executeUpdate()
    }
  }

}
