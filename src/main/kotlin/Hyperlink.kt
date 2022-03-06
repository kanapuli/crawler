data class Hyperlink(
    val url: String,
    val protocol: Protocol,
    val subdomains: Set<String>,
    val domain: String,
    val topLevelDomain: String,
    val rootDomain: String = "${domain}.${topLevelDomain}"
)