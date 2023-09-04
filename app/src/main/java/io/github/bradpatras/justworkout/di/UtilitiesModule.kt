package io.github.bradpatras.justworkout.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.UUID
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilitiesModule {
    @Singleton
    @Provides
    fun providesRandomUuid(): (() -> UUID) {
        return { UUID.randomUUID() }
    }
}