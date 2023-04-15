package streamer

import akka.stream.scaladsl.Source
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

trait Streamer {

  def wikiSource: Source[String, _] = {
    val iterable = (1 to 10).map(_ => document)
    val vlah = Source(iterable)
    vlah.takeWhile(d => d.hasText).map(_.title())
  }

  def document: Document = {
    val doc = Jsoup.connect("http://en.wikipedia.org/").get()
    doc
  }

}
