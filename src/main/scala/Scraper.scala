package hm.orz.sumpic

import scala.io.Source

/**
 * Twitterログサイトのページを解析し、ツイートを収集するクラス。
 */
object Scraper {

  // 広域定数。

  /** ツイート収集の限界値。 */
  val LOGGING_LIMIT: Int = 1000

  /** Twitterログの収集サイトのURLヘッダ */
  val URL_HEAD = "http://twilog.org/"

  // ケースクラス群。

  /** ツイート一つの情報を保持するクラス。 */
  case class TweetContent(hash: String, dt: String, content: String)

  /**
   * 解析 ＆ 収集。
   * @return ツイートの束。
   */
  def scraping(twitterId: String): List[TweetContent] = {
    // ネストしながら再帰でリスト作っていく関数を呼ぶ。
    getTweetLogs(List[TweetContent](), twitterId: String, 1)
  }

  def getTweetLogs(tweets: List[TweetContent], twitterId: String, depth: Int): List[TweetContent] = {
    // まず「予め設けた最大ツイート数」を超えてないようなら
    if (tweets.size < LOGGING_LIMIT) {
      // ページのテキストを取得する。
      val html = getTweetLogSiteHtmlText(twitterId, depth)
      // HTMLが「打ち止め」のようなら、コレ以上再帰せず戻る。
      if (html != null && html.length > 0) {
        // スクレイピングして「Tweetのリスト」を取得。それを末尾につけたリストを作り、
        getTweetLogs(tweets ::: scrapeHtml(html), twitterId, depth + 1)
      }
    }
    // ここまで来たってことは「何か打ち止め要素があった」ってこと。素直に引数返す。
    tweets
  }ひとひ

  def scrapeHtml(html: String): List[TweetContent] = {
    println(html)
    List()
  }

  /**
   * 指摘されたTwilogサイトのページのHtmlテキストを取得する。
   * 何らかのエラーの場合はnull,
   * 打ち止めの場合は""(から文字）を返す。
   * @param pageIndex ページの番号。
   * @return HTMLの文字列。
   */
  def getTweetLogSiteHtmlText(twitterId: String, pageIndex: Int): String = {
    val url = URL_HEAD + twitterId + "/" + pageIndex
    val html = getHtmlText(url)
    // Twilogサイトの特性。ツイート尽きたらこう出す。
    if (html.contains("ツイートが見つかりませんでした")) {
      ""
    } else {
      html
    }
  }

  /**
   * 指摘されたURLのページのHtmlテキストを取得する。
   * 何らかのエラーの場合はnull,
   * 成功の場合は、ページのHTMLテキストを返す。
   * @param url ページのURL。
   * @return HTMLの文字列。
   */
  def getHtmlText(url: String): String = {
    try {
      val src = Source.fromURL(url, "utf-8").getLines.toList
      // 文字列連結（元在った改行は消えてまうけど、スクレイプするからええやろ。
      src.reduceLeft {
        _ + _
      }
    } catch  {
      case e:Exception => null
    }
  }

}

