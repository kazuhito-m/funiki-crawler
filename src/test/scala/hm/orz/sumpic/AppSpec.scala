package hm.orz.sumpic

import org.specs2.mutable._

class AppSpec  extends Specification {

  "コンソールからの実行が出来る" should {
    "ともかくゼロを返してくる" in {
      // 実行
      val actual = App.run(Array())
      // 結果確認
      actual must equalTo(0)
    }
  }
}
