package hm.orz.sumpic

import org.specs2.mutable._
import scala.io.Source
import com.redis.RedisClient

class RedisAccesserSpec extends Specification {

  "Redisにアクセスし読み書き出来る" should {
    "名詞のリストを書き込むことが出来る" in {
      // arrange

      // テスト用の「名詞」データ
      val nones = List("コレ","名詞","データ")

      // act
      val actual = RedisAccesser.restoreNones(nones)

      // assert

      // 一度別手段で、Localhost上のRedisから生で読んでみる
      val r = new RedisClient("localhost", 6379)
      r.set("key", "some value")
      println(r.get("key").get)

      r.get("key").get must equalTo("some value")
    }
  }
}
