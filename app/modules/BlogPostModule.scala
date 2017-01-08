package modules

import com.google.inject.AbstractModule
import blogposts.EditBlogPostService
import repositories._

class BlogPostModule extends AbstractModule {

  override def configure() = {
    bind(classOf[BlogPostRepository])
      .to(classOf[repositories.impl.BlogPostRepository])

    bind(classOf[EditBlogPostService])
        .to(classOf[blogposts.impl.EditBlogPostService])


  }

}
