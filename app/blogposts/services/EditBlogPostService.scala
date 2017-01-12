package blogposts.services

import blogposts.{BlogPost, BlogPostViewModel}
import blogposts.repositories.BlogPostRepository
import com.google.inject.Inject
import tags.TagService

class EditBlogPostService @Inject()(repository: BlogPostRepository, tagService: TagService) {
  def create(post: BlogPostViewModel): BlogPostViewModel = {

    val tagIds = tagService.getOrCreateTagIds(post.tags)

    val result = repository.insert(post)


    BlogPostViewModel(result)
  }
}
