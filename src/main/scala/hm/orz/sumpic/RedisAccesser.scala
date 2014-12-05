package hm.orz.sumpic

import com.redis.RedisClient

/**
 * Created by kazuhito on 14/12/05.
 */
object RedisAccesser {

  /** RadisにList型で登録する「名詞」のキー */
  val NOUNS_KEY = "noun"

  def getClient():RedisClient = {
    new RedisClient("localhost", 6379)
  }

  def restoreNouns(nouns:List[String]) = {
    // 前準備。クライアント用意。
    val client = getClient()
    // 一度キーごと削除し…
    client.del(NOUNS_KEY)
    // 全量足す。
    nouns.foreach(client.rpush(NOUNS_KEY , _))
  }
  
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
