package io.github.bradpatras.justworkout.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.bradpatras.justworkout.utility.UuidProvider
import java.util.UUID
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilitiesModule {
    @Singleton
    @Provides
    fun providesUuidProvider(): UuidProvider {
        return UuidProvider { UUID.randomUUID() }
    }
}