package space.jay.mvvm_with_clean_architecture.data.network.retrofit.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltRetrofitService {

    @Singleton
    @Provides
    fun provideSettingService(): SettingService = SettingService()

    @Singleton
    @Provides
    fun providerServiceWiki(settingService : SettingService): ServiceWiki {
        return settingService.initApi(
            "https://en.wikipedia.org",
            ServiceWiki::class.java
        )
    }
}