package hm.orz.sumpic

import com.redis.RedisClient

/**
 * Radisに対する代表的な操作を司るクラス。<br/>
 *
 * キー文字列は内部に隠蔽し、値の取得・保存はメソッドのみで行う方針。
 * 「このプロジェクト中で必要な値」分の操作しか実装しない。
 */
object RedisAccesser {


  /** RadisにList型で登録する「名詞」のキー */
  val NOUNS_KEY = "noun"

  /**
   * Redisクライアントオブジェクトを取得する。
   * @return Redisの操作一式をくれるクライアントオブジェクト。
   */
  def getClient(): RedisClient = {
    new RedisClient("localhost", 6379)
  }

  /**
   * 「集計対象となるTwitterID」を取得する。
   * @return 取得できたTwitterID文字列。
   */
  def getStalkingTargetTwitterId() = getClient().get("stalking_target_twitter_id").get

  /**
   * 「名詞の集計を何件までやるか」値を取得する。
   * @return 取得できた数値。
   */
  def getNounsTopX() = getClient().get("nouns_top_x").get.toInt

  /**
   * 「ツイートを何件までスクレイピングするか」値を取得する。
   * @return 取得できた数値。
   */
  def analyzeTweetLimit(): Int = getClient().get("analyze_tweet_limit").get.toInt

  /**
   * 「名詞」リストを上書き保存する。
   * @param nouns 保存対象の「名詞」のリスト。
   */
  def restoreNouns(nouns:List[String]) = {
    // 前準備。クライアント用意。
    val client = getClient()
    // 一度キーごと削除し…
    client.del(NOUNS_KEY)
    // 全量足す。
    nouns.foreach(client.rpush(NOUNS_KEY , _))
  }

  /**
   * 「名詞」リストを取り出す。
   * @return 取得できた「名詞」リスト。
   */
  def getNouns():List[String] = {
    // 前準備。クライアント用意。
    val client = getClient()
    // 指定リストの長さを取得。
    val count = client.llen(NOUNS_KEY).get
    // 指定リストを「全件指定」で取得。
    val values = client.lrange(NOUNS_KEY,0,count.toInt)
    // Optionalのリストが帰ってきているはずだから、バラしながら回してリスト作ってく。
    values.get.map(_.get)
  }
}
