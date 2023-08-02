package com.idelburgo.showcaseapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonAPIManagerModule {

    @Provides
    @Singleton
    fun getBackendObject() : APIInterface {
        return PokemonRepository.getInstance()
    }
}