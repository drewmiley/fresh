package controllers

import akka.stream.Materializer
import play.api.http.ContentTypes
import play.api.libs.Comet
import play.api.mvc._

import javax.inject.{Inject, Singleton}

@Singleton
class ScalaCometController @Inject() (cc: ControllerComponents, materializer: Materializer) extends AbstractController(cc)
  with ScalaTicker {

  def index() = Action {
    Ok(views.html.streaming.scalacomet())
  }

  def streamClock() = Action {
    Ok.chunked(stringSource via Comet.string("parent.clockChanged")).as(ContentTypes.HTML)
  }
}
