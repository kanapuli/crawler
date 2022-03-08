class HtmlParser {

    fun retrieveHyperlinks(html: String): Sequence<Hyperlink> {
        return findUrls(html)
            .map { buildHyperlink(it) }
            .asSequence()
    }

    private fun findUrls(html: String): Set<String> {
        return A_TAG_PATTERN.findAll(html)
            .mapNotNull { it.groups.get(URL_GROUP_INDEX)?.value }
            .toSet()
    }

     fun buildHyperlink(url: String): Hyperlink {
        val urlWithoutProtocol = url.substringAfter("://")
        return Hyperlink(
            url = url,
            protocol = Protocol.valueOf(url.substringBefore(":").uppercase()),
            subdomains = parseSubdomnains(urlWithoutProtocol),
            domain = parseDomian(urlWithoutProtocol),
            topLevelDomain = urlWithoutProtocol.substringBefore("/").substringAfterLast("."),
        )
    }

    private fun parseSubdomnains(urlWithoutProtocol: String): Set<String> {
        return if (hasSubdomain(urlWithoutProtocol)) {
            setOf(urlWithoutProtocol.substringBefore("."))
        } else setOf()
    }

    private fun hasSubdomain(urlWithoutProtocol: String): Boolean {
        return urlWithoutProtocol.substringBefore("/").count { it == '.' } > 1
    }

    private fun parseDomian(urlWithoutProtocol: String): String {
       return  if (hasSubdomain(urlWithoutProtocol)) {
            urlWithoutProtocol.substringAfter(".").substringBefore(".")
        } else urlWithoutProtocol.substringBefore(".")
    }

    companion object {
        val A_TAG_PATTERN = Regex("<a(?:[^>]*)href=(['\\\"])(http.+?)\\1")
        const val URL_GROUP_INDEX: Int = 2
    }
}