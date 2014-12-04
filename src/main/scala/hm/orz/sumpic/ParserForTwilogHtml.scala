package hm.orz.sumpic

import scala.io.Source
import hm.orz.sumpic.Scraper.TweetContent

/**
 * TwitterログサイトのページのHTMLを解析するクラス。。
 */
object ParserForTwilogHtml {

  def parse(htmlText:String): List[TweetContent] = {


    // TODO Dummyなんであとで殺す
    println(htmlText)
    List[TweetContent]()
  }

}

