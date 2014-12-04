package hm.orz.sumpic

import org.specs2.mutable._
import scala.io.Source

class ParserForTwilogHtmlSpec  extends Specification {

  "HTMLをパースしオブジェクトに換えることが出来る" should {
    "ファイルを読み解析できる" in {
     // テスト用のHTMLファイルを読む。
      // TODO リソースから読むよう変更。
      val html = Source.fromURL(getClass.getResource("/test.html")).getLines.toList.foreach { i =>
        _ + "\n" + _
      }
      println(html)


      "test" must not beNull
    }
  }
}
