package controllers

import akka.stream.Materializer
import play.api.http.ContentTypes
import play.api.libs.Comet
import play.api.mvc._
import streamer.Streamer

import javax.inject._

@Singleton
class FreshController @Inject()(cc: ControllerComponents, materializer: Materializer) extends AbstractController(cc) with Streamer {

  def streamIndex() = Action {
    Ok(views.html.fresh.scalacomet())
  }

  def streamLoad() = Action {
    Ok.chunked(streamingSource via Comet.string("parent.streamLoaded")).as(ContentTypes.HTML)
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    //    TODO: Move out constants into env file
    Ok(views.html.fresh.index())
    //    TODO: REFACTOR / CLEAN UP DONE
  }
  
}
