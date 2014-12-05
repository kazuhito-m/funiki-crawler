package hm.orz.sumpic

import org.specs2.mutable._

class AppSpec  extends Specification {

  "コンソールからの実行が出来る" should {
    "ともかくゼロを返してくる" in {
      // arrange
      (new RedisAccesserSpec()).initConstValues() // 初期データ放り込む。
      // act
      val actual = App.run(Array())
      // assert
      actual must equalTo(0)
    }
  }
}
