package hm.orz.sumpic

import org.apache.lucene.analysis.ja._
import java.io.StringReader
import org.apache.lucene.analysis.ja.JapaneseTokenizer.Mode
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute
import hm.orz.sumpic.Scraper.TweetContent

/**
 * ツイートの束を元手に解析・集計・加工するクラス。
 */
object Analizer {

  /** 名詞を表すケースクラス。 */
  case class Noun(value:String,count:Int)

  /**
   * 指定された文字を形態素解析し名詞のみをリストに蓄える。
   * @param text 解析対象の文章。
   * @return 名詞の文字列がつまったリスト。
   */
  def getNouns(text:String):List[String] = {
    var nouns:List[String] = List.empty[String]

    // テキストを解析
    val tokenizer = new JapaneseTokenizer(new StringReader(text),null,true,Mode.NORMAL)

    // パーツに分けて回す。
    while(tokenizer.incrementToken()) {
      // トークン
      val charAtt = tokenizer.getAttribute(classOf[CharTermAttribute])
      // 品詞名
      val posAtt:PartOfSpeechAttribute = tokenizer.getAttribute(classOf[PartOfSpeechAttribute])

      // 品詞名を判定
      val hinshi:String = posAtt.getPartOfSpeech
      val noun = charAtt.toString
      if (hinshi.startsWith("名詞")) {
        if (hinshi.contains("固有名詞") || hinshi.contains("一般")) {
          // さすがに一文字のは「ノイズやろ…」と捉え無視しとく
          if (noun.length > 1) {
            nouns = nouns :+ noun
          }
        }
      }
    }
    tokenizer.close()
    // 処理完了。Listを返す。
    nouns
  }

  /**
   * 指定されたツイートの束を一つづつ形態素解析し名詞のみをリストに蓄える。
   * @param tweets ツイートの束。
   * @return 名詞の文字列がつまったリスト。
   */
  def getNounsFromTweets(tweets:List[TweetContent]):List[String] = {
    var nouns: List[String] = List.empty[String]
    tweets.foreach { i=>
      nouns = nouns ::: getNouns(i.content)
    }
    nouns
  }

  /**
   * 引数に指定された「名詞のリスト(重複在り)」を集計し、
   * 引数に指定された「上位何件」だけをリストで返す。
   * @param nouns 名詞リスト。
   * @param max 「いくつ返すか」のマックス。
   */
  def sumNounsTopX(nouns:List[String] ,max:Int):List[String] = {
    // TODO 仮実装（ではあるけども、せめて重複はのぞいとこうか）
    nouns.distinct.take(max)
  }
}

