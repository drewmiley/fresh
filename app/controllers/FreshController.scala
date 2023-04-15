package controllers

import akka.stream.Materializer
import play.api.http.ContentTypes
import play.api.libs.Comet
import play.api.mvc._
import streamer.Streamer

import javax.inject._

@Singleton
class FreshController @Inject()(cc: ControllerComponents, materializer: Materializer) extends AbstractController(cc) with Streamer {

  def stream() = Action {
    Ok(views.html.fresh.scalacomet())
  }

  def streamClock() = Action {
    Ok.chunked(stringSource via Comet.string("parent.clockChanged")).as(ContentTypes.HTML)
  }

  def index() = Action { implicit request: Request[AnyContent] =>
//    TODO: How to implement \/
    //    Load index
    //    Add scala comet + link
    //    Create ticker source
    //    Move wiki scraper into source
    //    Display wiki scraping using comet for 1 to 5/10 times
    //    Create pool fixture scraper
    //    Replace wiki scraper with pool fixture scraper for 1 to 5/10 times
    //    Make it clever enough to stop at end of fixtures
    //    Move out constants into env file
    Ok(views.html.fresh.index())
  }
  
}
