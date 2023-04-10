package controllers

import play.api.http.ContentTypes
import play.api.libs.EventSource
import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class ScalaEventSourceController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with ScalaTicker {

  def index() = Action {
    Ok(views.html.streaming.scalaeventsource())
  }

  def streamClock() = Action {
    Ok.chunked(stringSource via EventSource.flow).as(ContentTypes.EVENT_STREAM)
  }

}