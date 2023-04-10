package space.jay.mvvm_with_clean_architecture._core.domain

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import space.jay.mvvm_with_clean_architecture._core.common.di.DispatcherIO
import space.jay.mvvm_with_clean_architecture._core.data.RepositoryPokemonGo
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetPokemon @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val repository : RepositoryPokemonGo,
) {

    suspend operator fun invoke(number : Int) = withContext(dispatcher) {
        return@withContext repository.getPokemon(number)
    }

    suspend operator fun invoke(id : String) = withContext(dispatcher) {
        return@withContext repository.getPokemon(id)
    }
}