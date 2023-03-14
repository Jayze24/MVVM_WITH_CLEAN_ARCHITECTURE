package space.jay.mvvm_with_clean_architecture.data.network.retrofit.mapper

import space.jay.mvvm_with_clean_architecture.base.BaseMapper
import space.jay.mvvm_with_clean_architecture.data.network.retrofit.data.DataWiki
import space.jay.mvvm_with_clean_architecture.domain.entity.EntityWiki

class MapperWiki : BaseMapper<DataWiki, EntityWiki?> {

    override fun map(input : DataWiki) : EntityWiki? = with(input) {
        if (pageid == null) {
            null
        } else {
            EntityWiki(
                id = pageid,
                title = title,
                content = extract,
                image = originalimage?.source,
                link = content_urls?.mobile?.page ?: content_urls?.desktop?.page,
                description = description
            )
        }
    }

}