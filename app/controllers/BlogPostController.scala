package controllers

import play.api.mvc.{Action, Controller}

class BlogPostController extends Controller {

  def index(id: String) = Action {
    Ok("")
  }
}
