package com.madarsoft.users.di

import com.madarsoft.users.data.repository.InsertUserRepoImp
import com.madarsoft.users.data.repository.ShowUserRepoImp
import com.madarsoft.users.domain.repository.InsertUserRepo
import com.madarsoft.users.domain.repository.ShowUserRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }


}
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun insertUserRepo(impl: InsertUserRepoImp): InsertUserRepo
    @Singleton
    @Binds
    abstract fun showUsersRepo(impl: ShowUserRepoImp): ShowUserRepo

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

