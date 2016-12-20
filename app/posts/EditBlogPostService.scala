package posts

import models.BlogPost

trait EditBlogPostService {

  def create(post: BlogPost): BlogPost

}
