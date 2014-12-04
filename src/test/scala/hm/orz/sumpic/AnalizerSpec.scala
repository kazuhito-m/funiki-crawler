package hm.orz.sumpic

import org.specs2.mutable._

class AnalizerSpec  extends Specification {

  "ツイートの束を分析出来る。" should {
    "文字列を線形粗解析し名詞を蓄える" in {
      // arrange
      val words = "私の名前は三浦一仁です。"

      // act
      val actual = Analizer.getNouns(words)

      // assert
      actual.size must equalTo(4)
    }
  }
}
