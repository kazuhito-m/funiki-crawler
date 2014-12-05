package hm.orz.sumpic

import org.specs2.mutable._
import scala.io.Source
import com.redis.RedisClient

class RedisAccesserSpec extends Specification {

  /**
   * テスト用ユティリティメソッド。
   * 予め「Redisに求められる定数的なKey:Value」を設定する。
   */
  def initConstValues() = {
    val redis = RedisAccesser.getClient()
    redis.set("stalking_target_twitter_id","kazuhito_m")
    redis.set("nouns_top_x",100)
    redis.set("analyze_tweet_limit",1000)
  }

  "Redisにアクセスし読み書き出来る" should {
    "名詞のリストを書き込むことが出来る" in {
      // arrange
      initConstValues()  // Redisに初期値突っ込む。

      // テスト用の「名詞」データ
      val expected = List("コレ","名詞","データ","だってばよ！")

      // act
      RedisAccesser.restoreNouns(expected)
      val actual:List[String] = RedisAccesser.getNouns()

      // assert
      actual must equalTo(expected)
    }
  }
}
