package modules

import com.google.inject.AbstractModule
import repositories._

class BlogPostModule extends AbstractModule {

  override def configure() = {
    bind(classOf[BlogPostRepository])
      .to(classOf[impl.BlogPostRepository])

  }

}
