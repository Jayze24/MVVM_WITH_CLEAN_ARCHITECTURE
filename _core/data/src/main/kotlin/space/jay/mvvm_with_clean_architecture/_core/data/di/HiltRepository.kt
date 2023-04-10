package space.jay.mvvm_with_clean_architecture._core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryPokemonGo
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryWiki
import space.jay.mvvm_with_clean_architecture._core.data.repository.RepositoryPokemonGoImpl
import space.jay.mvvm_with_clean_architecture._core.data.repository.RepositoryWikiImpl

@Module
@InstallIn(SingletonComponent::class)
interface HiltRepository {

    @Binds
    fun bindRepositoryWiki(repository: RepositoryWikiImpl): RepositoryWiki

    @Binds
    fun bindRepositoryPokemonGo(repository: RepositoryPokemonGoImpl): RepositoryPokemonGo
}