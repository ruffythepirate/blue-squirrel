package services

import models.BlogPost

trait BlogPostService {

  def create(post: BlogPost): BlogPost

}
