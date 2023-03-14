package space.jay.mvvm_with_clean_architecture.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.jay.mvvm_with_clean_architecture.domain.boundary.BoundaryWiki

@InstallIn(SingletonComponent::class)
@Module
abstract class HiltBindRepository {

    @Binds
    abstract fun bindBoundaryRepositoryWiki(repository: RepositoryWiki): BoundaryWiki
}