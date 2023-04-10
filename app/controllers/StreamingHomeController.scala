package controllers

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject

class StreamingHomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action {
    Ok(views.html.streaming.index())
  }

}
