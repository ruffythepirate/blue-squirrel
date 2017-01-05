package repositories.impl

import anorm.SqlParser._
import anorm.{~, _}
import com.google.inject.Inject
import models.{BlogPost, Page}
import org.joda.time.DateTime
import play.api.Logger
import play.api.db.Database


class BlogPostRepository @Inject() ( db: Database) extends repositories.BlogPostRepository {

  // -- Parsers

  /**
    * Parse a BlogPost from a ResultSet
    */
  val blogPost = {
    get[Long]("blogposts.id") ~
      get[String]("blogposts.title") ~
      get[String]("blogposts.body") ~
      get[DateTime]("blogposts.updated_date") ~
      get[DateTime]("blogposts.created_date") map {
      case id ~ title ~ body ~ updatedDate ~ createdDate => BlogPost(Some(id), title, body, Some(updatedDate), Some(createdDate))
    }
  }

  // -- Queries
  BlogPost
  /**
    * Retrieve a blogPost from the id.
    */
  def findById(id: Long): Option[BlogPost] = {
    db.withConnection { implicit connection =>
      SQL("select * from blogposts where id = {id}").on('id -> id)
        .as(blogPost.singleOpt)
    }
  }

  /**
    * Return a page of (BlogPost).
    *
    * @param page Page to display
    * @param pageSize Number of blogPosts per page
    * @param orderBy BlogPost property used for sorting
    * @param filter Filter applied on the name column
    */
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Page[BlogPost] = {

    val offest = pageSize * page

    db.withConnection { implicit connection =>

      val blogPosts = SQL(
        """
          select * from blogposts
          where blogposts.title like {filter}
          order by {orderBy} nulls last
          limit {pageSize} offset {offset}
        """).on(
        'pageSize -> pageSize,
        'offset -> offest,
        'filter -> filter,
        'orderBy -> orderBy).as(blogPost *)

      val totalRows = SQL(
        """
          select count(*) from blogposts
          where blogposts.title like {filter}
        """).on(
        'filter -> filter).as(scalar[Long].single)

      Page(blogPosts, page, offest, totalRows)

    }

  }

  /**
    * Retrieve all blogPost.
    *
    * @return
    */
  def findAll(): List[BlogPost] = {
    db.withConnection { implicit connection =>
      try {
        SQL("select * from blogposts order by name").as(blogPost *)
      } catch {
        case ex: Exception => Logger.info("ERROR", ex); Nil
      }
    }
  }

  /**
    * Update a blogPost.
    *
    * @param id The blogPost id
    * @param blogPost The employee values.
    */
  def update(id: Long, blogPost: BlogPost): Int = {
    db.withConnection { implicit connection =>
      SQL(
        """
          update blogPosts
          set name = {name}, address = {address}, designation = {designation}
          where id = {id}
        """).on(
        'id -> id,
        'title -> blogPost.title,
        'body -> blogPost.body)
        .executeUpdate()
    }
  }

  /**
    * Insert a new blogPost.
    *
    * @param blogPost The employee values.
    */
  def insert(blogPost: BlogPost): BlogPost = {
    val id = db.withConnection { implicit connection =>
      SQL("""insert into blogposts (id, title, body) values ({id}, {title}, {body})""")
        .on(
        'id -> Option.empty[Long],
        'title -> blogPost.title,
        'body -> blogPost.body )
        .executeInsert()
    }

    findById(id.get).get
  }

  /**
    * Delete a blogPost.
    *
    * @param id Id of the blogPost to delete.
    */
  def delete(id: Long): Int = {
    db.withConnection { implicit connection =>
      SQL("delete from blogposts where id = {id}").on('id -> id).executeUpdate()
    }
  }

}
