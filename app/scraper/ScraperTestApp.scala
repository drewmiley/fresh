package scraper

object ScraperTestApp {

  def main(args: Array[String]): Unit = {
    val doc = Scraper.document(1)
    val title = doc.title()
    println(title)
    val totalPages = Scraper.getTotalFixturePages
    println(totalPages)
    val fixtures = Scraper.getFixturesForPage(1, Option("BLUE BELL A"))
    println(fixtures)
    println("RUNNING APP")
  }

}