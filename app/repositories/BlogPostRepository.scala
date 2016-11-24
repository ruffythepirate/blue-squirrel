package repositories

import models.{BlogPost, Page}

trait BlogPostRepository {

  def list(page: Int, pageSize: Int, orderBy: Int, filter: String): Page[BlogPost]
}
