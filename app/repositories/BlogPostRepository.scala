package repositories

import models.{BlogPost, Page}

trait BlogPostRepository {

  def insert(post: BlogPost): BlogPost

  def list(page: Int, pageSize: Int, orderBy: Int, filter: String): Page[BlogPost]
}
