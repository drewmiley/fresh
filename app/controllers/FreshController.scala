package controllers

import akka.stream.Materializer
import play.api.data.Form
import play.api.http.ContentTypes
import play.api.libs.Comet
import play.api.mvc._
import streamer.Streamer

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class FreshController @Inject()(cc: MessagesControllerComponents, materializer: Materializer) extends MessagesAbstractController(cc) with Streamer {
  import SearchForm._

  private val postUrl = routes.FreshController.indexSearch()

  def index() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.index(form, postUrl))
  }

  def indexSearch() = Action {  implicit request: MessagesRequest[AnyContent] =>
        val errorFunction = { formWithErrors: Form[Search] =>
          // This is the bad case, where the form had validation errors.
          // Let's show the user the form again, with the errors highlighted.
          // Note how we pass the form with errors to the template.
          BadRequest(views.html.index(form, postUrl))
        }

        val successFunction = { search: Search =>
//          TODO: Need to get search into this call below and pass through
          Redirect(routes.FreshController.streamIndex())
        }

        val formValidationResult = form.bindFromRequest()
        formValidationResult.fold(errorFunction, successFunction)
  }

  def streamIndex() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.scalacomet())
  }

  def streamLoad() = Action.async { implicit request: MessagesRequest[AnyContent] =>
//    TODO: Move filterTeam into templates and use forms
    val filterTeam = Option("BLUE BELL A")
    streamingSourceFuture(filterTeam) map { streamingSource =>
//      TODO: Order fixtures correctly
      Ok.chunked(streamingSource via Comet.string("parent.streamLoaded")).as(ContentTypes.HTML)
    }
  }
  
}
