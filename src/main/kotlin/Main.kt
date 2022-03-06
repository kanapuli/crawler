import java.net.URL


class Crawler(private val seedUrl: String) {

    fun start() {
       println( retrieveWebsiteContents(seedUrl))
    }
   private fun retrieveWebsiteContents(url: String): String {
       return URL(url).readText()
   }
}

fun main() {
 val crawler = Crawler("https://kotlinlang.org/")
    crawler.start()
}