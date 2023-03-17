package space.jay.mvvm_with_clean_architecture.data.network.retrofit

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.jay.mvvm_with_clean_architecture.data.source.SourceWiki

@InstallIn(SingletonComponent::class)
@Module
abstract class HiltBindSource {

    @Binds
    abstract fun bindSourceSearchBook(api: SourceWikiApi): SourceWiki
}