package controllers

import play.api.mvc._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class FreshController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
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
