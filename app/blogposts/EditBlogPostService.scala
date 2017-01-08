package blogposts

import models.BlogPost

trait EditBlogPostService {

  def create(post: BlogPost): BlogPost

}
