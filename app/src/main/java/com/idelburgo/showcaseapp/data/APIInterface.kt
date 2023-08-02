package com.idelburgo.showcaseapp.data

import com.idelburgo.showcaseapp.data.models.PokemonModel
import okhttp3.ResponseBody

interface APIInterface {
    suspend fun getPokemon(pokemon_id: Int): PokemonModel?
}