import java.net.URL


class Crawler(private val seedUrl: String) {
    private val htmlParser = HtmlParser()
    fun start() {
        val seedHtml = retrieveWebsiteContents(seedUrl)
        val urls: Set<String> = htmlParser.findUrls(seedHtml)
        println(urls)

    }

    private fun retrieveWebsiteContents(url: String): String {
        return URL(url).readText()
    }
}

fun main() {
    val crawler = Crawler("https://kotlinlang.org/")
    crawler.start()
}