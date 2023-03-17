package space.jay.mvvm_with_clean_architecture.data.network.retrofit.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltRetrofitDao {

    @Singleton
    @Provides
    fun provideSettingService(): SettingDao = SettingDao()

    @Singleton
    @Provides
    fun providerServiceWiki(settingDao : SettingDao): DaoWiki {
        return settingDao.initApi(
            "https://en.wikipedia.org",
            DaoWiki::class.java
        )
    }
}