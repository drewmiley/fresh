package controllers

// TODO: FixtureType should use only allowed options
//case class FixtureType()
//
//object Home extends FixtureType
//object Away extends FixtureType
//object All extends FixtureType

object SearchForm {

  import play.api.data.Form
  import play.api.data.Forms._

  case class Search(name: String, fixtureType: String)

  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val form = Form(
    mapping(
      "name" -> text,
      "fixtureType" -> text
    )(Search.apply)(Search.unapply)
  )
}
