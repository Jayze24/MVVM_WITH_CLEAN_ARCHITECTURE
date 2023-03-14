package space.jay.mvvm_with_clean_architecture.domain.entity

data class EntityWiki(
    val id : Long? = null,
    val title : String? = null,
    val content : String? = null,
    val image : String? = null,
    val link : String? = null,
    val description : String? = null
)