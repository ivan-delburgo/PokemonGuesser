package com.idelburgo.showcaseapp.data

import com.idelburgo.showcaseapp.data.models.PokemonModel
import com.idelburgo.showcaseapp.data.models.toPokemonModel

class PokemonRepository (private val apiInterface: PokemonAPIInterface): APIInterface {
    companion object {

        @Volatile private var INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PokemonRepository(
                    RetrofitInstance.backendInstance.create(
                        PokemonAPIInterface::class.java
                    ))
            }
    }

    override suspend fun getPokemon(pokemon_id: Int): PokemonModel? {
        return try {
            val response = apiInterface.getPokemon(pokemon_id)
            response?.toPokemonModel()
        } catch (e: Exception) {
            println("$e")
            null
        }
    }
}