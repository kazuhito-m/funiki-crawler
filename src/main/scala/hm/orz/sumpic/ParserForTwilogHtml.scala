package hm.orz.sumpic

import hm.orz.sumpic.Scraper.TweetContent

import org.jsoup._
import org.jsoup.nodes._
import org.jsoup.select._


/**
 * TwitterログサイトのページのHTMLを解析するクラス。。
 */
object ParserForTwilogHtml {

  def parse(htmlText: String): List[TweetContent] = {

    var tweets = List.empty[TweetContent]

    // JSorpにHTMLを読ませる。
    val doc: Document = Jsoup.parse(htmlText)

    // Tweet部分だけを取得
    val elems: Elements = doc.select(".tl-tweet")

    elems.toArray.foreach { i =>
        // HTMLを解析、然るべき箇所からデータを取ってくる。
        val elm: Element = i.asInstanceOf[Element]
        val hash = elm.attr("id").replace("tw", "")
        val date = elm.select(".tl-time").text
        val content = elm.select(".tl-text").text
        val postType = elm.select(".tl-posted").text
        val time = postType.replace("posted at ","")

        // 絞り込み条件は「コメントが空文字(ツイ消しと思われる）と、
        // postTypeが”postd at "のみ（リツイート除く）しか投影しない。
        if (!content.isEmpty && postType.startsWith("posted at ")) {

          // caseクラスに詰める。
          val tweet = TweetContent(hash, s"$date $time", content)
          // 結果リストに足す。
          tweets = tweets :+ tweet

          // Debug用。削除しても良い。
//          println("---------------------------------------------------------------------------------------------")
//          println("hash:" + tweet.hash)
//          println("dt:" + tweet.dt)
//          println("content:" + tweet.content)
//          println("件数:" + tweets.size)
        }
    }

    // 貯めた「Tweetのリスト」を返す。
    tweets
  }

}

