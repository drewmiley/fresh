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
//    TODO: How to implement \/
    //    Load index DONE
    //    Add scala comet + link DONE
    //    Create ticker source DONE
    //    Move wiki scraper into source DONE
    //    Display wiki scraping using comet for 1 to 5/10 times DONE

    //    Create pool fixture scraper
    //    Replace wiki scraper with pool fixture scraper for 1 to 5/10 times
    //    Make it clever enough to stop at end of fixtures
    //    Move out constants into env file
    Ok(views.html.fresh.index())
    //    REFACTOR / CLEAN UP
  }
  
}
