package hm.orz.sumpic

import org.specs2.mutable._
import scala.io.Source
import com.redis.RedisClient

class RedisAccesserSpec extends Specification {

  "Redisにアクセスし読み書き出来る" should {
    "名詞のリストを書き込むことが出来る" in {
      // arrange

      // テスト用の「名詞」データ
      val nones = List("コレ","名詞","データ","だってばよ！")

      // act
      RedisAccesser.restoreNones(nones)
      val actual:List[String] = RedisAccesser.getNones()

      // assert
      actual must equalTo(nones)
    }
  }
}
