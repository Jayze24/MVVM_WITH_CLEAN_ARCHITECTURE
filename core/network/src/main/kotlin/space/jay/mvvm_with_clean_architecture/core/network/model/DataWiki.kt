package space.jay.mvvm_with_clean_architecture.core.network.model

import space.jay.mvvm_with_clean_architecture.core.model.wiki.EntityWiki

data class DataWiki(
    val pageid : Long? = null,
    val title : String? = null,
    val extract : String? = null,
    val content_urls : DataWikiLink? = null,
    val description : String? = null,
    val originalimage : DataWikiImage? = null,
)

data class DataWikiLink(
    val desktop : DataWikiPage? = null,
    val mobile : DataWikiPage? = null
)

data class DataWikiPage(
    val page : String? = null,
    val revisions : String? = null,
    val edit : String? = null,
    val talk : String? = null
)

data class DataWikiImage(
    val source : String? = null,
    val width : Int? = null,
    val height : Int? = null
)

fun DataWiki.asEntity() = EntityWiki(
    id = pageid,
    title = title,
    content = extract,
    image = originalimage?.source,
    link = content_urls?.mobile?.page ?: content_urls?.desktop?.page,
    description = description
)