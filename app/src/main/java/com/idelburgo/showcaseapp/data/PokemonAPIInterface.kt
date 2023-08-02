package com.idelburgo.showcaseapp.data

import com.idelburgo.showcaseapp.data.models.PokemonAPIModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPIInterface {
    @GET("pokemon/{poke_id}")
    suspend fun getPokemon(
        @Path("poke_id") poke_id: Int
    ) : PokemonAPIModel?
}