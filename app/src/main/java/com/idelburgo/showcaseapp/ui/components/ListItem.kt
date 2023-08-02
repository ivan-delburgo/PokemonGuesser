package com.idelburgo.showcaseapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.idelburgo.showcaseapp.data.models.PokemonModel

@Composable
fun ListItem(
    guessing: String,
    pokemonToGuess: PokemonModel? = null,
    position: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxSize()
    ) {
        Row() {
            Text(
                text = "Attempt $position: ",
                modifier = Modifier.padding(all = 8.dp)
            )
            Text(
                buildAnnotatedString {
                    guessing.forEachIndexed { index, letter ->
                        var guessingColor = Color.Black

                        if (pokemonToGuess?.name?.getOrNull(index) != null
                            && pokemonToGuess.name[index] == letter
                        ) {
                            guessingColor = Color.Green
                        } else if (pokemonToGuess != null && (letter in pokemonToGuess.name)) {
                            guessingColor = Color.Yellow
                        }
                        withStyle(style = SpanStyle(color = guessingColor)) {
                            append(letter)
                        }
                    }
                },
                modifier = Modifier.padding(all = 8.dp)
            )
        }
    }
}