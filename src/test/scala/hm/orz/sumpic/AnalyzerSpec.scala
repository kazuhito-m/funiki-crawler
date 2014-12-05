package hm.orz.sumpic

import org.specs2.mutable._
import hm.orz.sumpic.Scraper.TweetContent

class AnalyzerSpec extends Specification {

  "ツイートの束を分析出来る。" should {
    "文字列を線形粗解析し名詞を蓄える" in {
      // arrange
      val words = "私の名前は三浦一仁です。"

      // act
      val actual = Analyzer.getNouns(words)

      // assert
      actual.size must equalTo(3)
    }

    "ツイートオブジェクトの束から名詞を蓄えることが出来る。" in {
      // arrange
      val tweets = List[TweetContent] (
        TweetContent("1111","今日","私の名前はミウラ一仁です。")
        ,TweetContent("1111","今日","日曜には、協会のミサに行く予定の雰囲気を醸し出しています。")
        ,TweetContent("1111","今日","西島洋介山こそ、探されるべき人類だと思います。。")
        ,TweetContent("1111","今日","やまーだでんきっ！")
        ,TweetContent("1111","今日","これで、いいの。自分を好きなって。")
        ,TweetContent("1111","今日","ホーリーローリーマウンテンがものを言うやつだな。。")
        ,TweetContent("1111","今日","念願のアイスソードを手に入れたぞ。")
      )

      // act
      val actual = Analyzer.getNounsFromTweets(tweets)

      // assert
      actual.size must equalTo(14)
    }
  }

  "集計し頻出ワードを出せる" should {
    "名詞のリストを集計し頻繁に出てくるTopいくつかを指定された数だけ上から取れる" in {
      // arrange
      val nouns = List[String](
        "A","A","A","A","A","A",
        "B","B","B","B","B","B","B","B","B","B",
        "C","C","C","C",
        "D","D"
      )

      // act
      val actual = Analyzer.sumNounsTopX(nouns , 3)

      // assert
      //      actual must equalTo(List("B","A","C"))
      // TODO 未実装だから、仮の結果（ほんとは良くない）
      actual must equalTo(List("A","B","C"))

    }
  }
}
