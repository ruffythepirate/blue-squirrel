package modules

import com.google.inject.AbstractModule
import repositories._
import services._
import services.BlogPostService

class BlogPostModule extends AbstractModule {

  override def configure() = {
    bind(classOf[BlogPostRepository])
      .to(classOf[repositories.impl.BlogPostRepository])

    bind(classOf[BlogPostService])
        .to(classOf[services.impl.BlogPostService])


  }

}
