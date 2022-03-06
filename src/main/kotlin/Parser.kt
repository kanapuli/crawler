class HtmlParser {
    fun findUrls(html: String): Set<String> {
        return A_TAG_PATTERN.findAll(html)
            .mapNotNull { it.groups.get(URL_GROUP_INDEX)?.value }
            .toSet()
    }

    companion object {
        val A_TAG_PATTERN = Regex("<a(?:[^>]*)href=(['\\\"])(http.+?)\\1")
        const val URL_GROUP_INDEX: Int = 2
    }
}