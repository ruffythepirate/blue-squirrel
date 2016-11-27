package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}

class EditorController @Inject() () extends Controller {

  def index = Action {

    Ok(views.html.blogposteditor())
  }
}
