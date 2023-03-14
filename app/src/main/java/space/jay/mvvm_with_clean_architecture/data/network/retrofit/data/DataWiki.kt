package space.jay.mvvm_with_clean_architecture.data.network.retrofit.data

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