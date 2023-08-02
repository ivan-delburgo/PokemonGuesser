package com.idelburgo.showcaseapp.ui.overview

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idelburgo.showcaseapp.data.APIInterface
import com.idelburgo.showcaseapp.data.PokemonRepository
import com.idelburgo.showcaseapp.data.models.PokemonModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val pokemonRepository: APIInterface
): ViewModel(){
    private val _itemList = mutableStateListOf<String>()
    val itemList: List<String>
        get() = _itemList

    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue = _textFieldValue.asStateFlow()

    private val _pokemonToGuess = MutableStateFlow<PokemonModel?>(null)
    val pokemonToGuess = _pokemonToGuess.asStateFlow()

    private val _endGame = MutableStateFlow(false)
    val endGame = _endGame.asStateFlow()


    init {
        _pokemonToGuess.value = null
    }

    fun startGame() {
        viewModelScope.launch {
            _pokemonToGuess.value = pokemonRepository.getPokemon(Random.nextInt(1, 1010))
        }
    }

    fun submit() {
        _itemList.add(0,_textFieldValue.value)
        if (itemList.size == 10 || (pokemonToGuess.value?.name == _textFieldValue.value)) {
            _endGame.value = true
        } else {
            _textFieldValue.value = ""
        }
    }


    fun setTextField(text: String) {
        _textFieldValue.value = text
    }
}

