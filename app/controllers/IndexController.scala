package controllers

import blogposts.repositories.BlogPostRepository
import blogposts.services.ReadBlogPostService
import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import profiles.ProfileService

class IndexController @Inject() (readBlogPostService: ReadBlogPostService, profileService: ProfileService) extends Controller {

  def index = Action {

    val blogPosts = readBlogPostService.list(0, 10, 1, "%")

    val profile = profileService.getProfile
    Ok(views.html.index.index(profile, blogPosts))
  }

}
