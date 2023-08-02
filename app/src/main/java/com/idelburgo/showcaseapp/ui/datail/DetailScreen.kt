package com.idelburgo.showcaseapp.ui.datail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.idelburgo.showcaseapp.R


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    item: Int,
    success: Boolean,
    onReset: () -> Unit
) {
    val pokemonToGuess by viewModel.pokemonToShow.collectAsState()
    viewModel.getPokemon(item)
    if (pokemonToGuess != null) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(text = if (success) {
                stringResource(id = R.string.detail_success)
            } else {
                stringResource(id = R.string.detail_failed)
                   },
                style = MaterialTheme.typography.headlineMedium,
                color = if (success) Color.Green else Color.Red)
            Text(text = stringResource(id = R.string.detail_it_was),
                style = MaterialTheme.typography.headlineMedium)
            Text(
                text = pokemonToGuess!!.name,
                modifier = Modifier,
                style = MaterialTheme.typography.headlineLarge
            )
            AsyncImage(
                model = pokemonToGuess!!.imageUrl,
                contentDescription = stringResource(id = R.string.detail_image_content_description, pokemonToGuess!!.name),
                modifier = Modifier.size(200.dp, 200.dp)
            )
            Button(onClick = onReset) {
                Text (
                    text = stringResource(id = R.string.play_again_cta)
                )
            }
        }
    } else {
        // here a loading screen can be shown
    }
}