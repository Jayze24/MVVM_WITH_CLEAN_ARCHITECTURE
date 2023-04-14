package space.jay.mvvm_with_clean_architecture.core.domain

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.mvvm_with_clean_architecture.core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture.core.data.RepositoryPokemonGo
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetListMegaPokemon @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val repository : RepositoryPokemonGo,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        return@withContext repository.getListMegaPokemon()
    }
}