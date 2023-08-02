package com.idelburgo.showcaseapp.ui.datail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idelburgo.showcaseapp.data.APIInterface
import com.idelburgo.showcaseapp.data.models.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pokemonRepository: APIInterface
): ViewModel() {

    private val _pokemonToShow = MutableStateFlow<PokemonModel?>(null)
    val pokemonToShow = _pokemonToShow.asStateFlow()

    fun getPokemon(id: Int) {
        viewModelScope.launch {
            _pokemonToShow.value = pokemonRepository.getPokemon(id)
        }
    }
}
