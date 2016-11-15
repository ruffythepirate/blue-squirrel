package repositories.impl

import anorm.SqlParser._
import anorm.{~, _}
import models.{BlogPost, Page}
import play.api.Logger
import play.api.db.DB

import play.api.Play.current


object BlogPostRepository extends repositories.BlogPostRepository {

  // -- Parsers

  /**
    * Parse a BlogPost from a ResultSet
    */
  val blogPost = {
    get[Long]("blogPost.id") ~
      get[String]("blogPost.title") ~
      get[String]("blogPost.body") map {
      case id ~ title ~ body => BlogPost(id, title, body)
    }
  }

  // -- Queries
  BlogPost
  /**
    * Retrieve a blogPost from the id.
    */
  def findById(id: Long): Option[BlogPost] = {
    DB.withConnection { implicit connection =>
      SQL("select * from blogPost where id = {id}").on('id -> id)
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

    DB.withConnection { implicit connection =>

      val blogPosts = SQL(
        """
          select * from blogPost
          where blogPost.title like {filter}
          order by {orderBy} nulls last
          limit {pageSize} offset {offset}
        """).on(
        'pageSize -> pageSize,
        'offset -> offest,
        'filter -> filter,
        'orderBy -> orderBy).as(blogPost *)

      val totalRows = SQL(
        """
          select count(*) from blogPost
          where blogPost.title like {filter}
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
    DB.withConnection { implicit connection =>
      try {
        SQL("select * from blogPost order by name").as(blogPost *)
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
    DB.withConnection { implicit connection =>
      SQL(
        """
          update blogPost
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
  def insert(blogPost: BlogPost): Option[Long] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into blogposts values (
    		{id}, {title}, {body} )
        """).on(
        'id -> Option.empty[Long],
        'name -> blogPost.title,
        'address -> blogPost.body)
        .executeInsert()
    }
  }

  /**
    * Delete a blogPost.
    *
    * @param id Id of the blogPost to delete.
    */
  def delete(id: Long): Int = {
    DB.withConnection { implicit connection =>
      SQL("delete from blogposts where id = {id}").on('id -> id).executeUpdate()
    }
  }

}
