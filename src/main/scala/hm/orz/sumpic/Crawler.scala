package hm.orz.sumpic

/**
 * クロウラー。
 * コレ自身は「トランザクションの全体制御」のみを司る。
 */
class Crawler {

  def crawl():Int = {

    // まず、Twitterのログ収集サイトから、一定分のツイートを読み込む。
    val tweets = Scraper.scraping("kazuhito_m")

    // ツイートすべてに形態素解析をかけ、名詞だけを取り出す。
    val nouns: List[String] = Analizer.getNounsFromTweets(tweets)

    // TODO 名詞を集計し、TOP100程度を出す。

    // TODO Redisに接続し、名詞を書き出す。

    0
  }

}
