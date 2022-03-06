import kotlin.text.Regex;
class HtmlParser {

    fun findUrls(html: String): Set<String> {
       println( A_TAG_PATTERN.findAll(html).first().groups.get(2)?.value)
        return setOf()
    }

    companion object {
         val A_TAG_PATTERN = Regex("<a(?:[^>]*)href=(['\\\"])(http.+?)\\1")
    }
}