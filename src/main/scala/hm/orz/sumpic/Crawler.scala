package hm.orz.sumpic

/**
 * クロウラー。
 * コレ自身は「トランザクションの全体制御」のみを司る。
 */
class Crawler {

  def crawl():Int = {

    // 予め、欲しい設定値はRedisから取得しておく。
    val twitterId:String = RedisAccesser.getStalkingTargetTwitterId()
    val nounsTopX:Int = RedisAccesser.getNounsTopX()
    val analyzeTweetLimit:Int = RedisAccesser.analyzeTweetLimit()



    // Twitterのログ収集サイトから、一定分のツイートを読み込む。
    val tweets = Scraper.scraping(twitterId , analyzeTweetLimit)

    // ツイートすべてに形態素解析をかけ、名詞だけを取り出す。
    val nouns: List[String] = Analyzer.getNounsFromTweets(tweets)

    // 名詞を集計し、TOP100程度を出す。
    val topNouns: List[String] = Analyzer.sumNounsTopX(nouns , nounsTopX)

    // Redisに接続し、名詞を書き出す。
    RedisAccesser.restoreNouns(topNouns)

    0
  }

}
