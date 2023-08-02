package com.idelburgo.showcaseapp.ui.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.idelburgo.showcaseapp.R
import com.idelburgo.showcaseapp.ui.components.ListItem


@Composable
fun MainGameScreen(
    modifier: Modifier = Modifier,
    viewModel: OverviewViewModel = hiltViewModel(),
    onGameFinished: (Boolean, Int) -> Unit
) {
    val pokemonToGuess by viewModel.pokemonToGuess.collectAsState()
    if (pokemonToGuess != null) {
        GameScreen(
            viewModel = viewModel,
            onGameFinished = onGameFinished
        )
    } else {
        StartGameScreen() {
            viewModel.startGame()
        }
    }
}

@Composable
fun StartGameScreen(
    onStartClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.game_explanation),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = R.string.game_catcher),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(id = R.string.color_legend),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = onStartClicked) {
            Text(stringResource(id = R.string.start_game_cta), modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: OverviewViewModel,
    onGameFinished: (Boolean, Int) -> Unit
) {
    Column {
        val textState = viewModel.textFieldValue.collectAsState(initial = "")
        val pokemonToGuess = viewModel.pokemonToGuess.collectAsState()
        val shouldEndGame = viewModel.endGame.collectAsState()
        if (shouldEndGame.value) {
            onGameFinished(
                pokemonToGuess.value?.name == textState.value,
                pokemonToGuess.value?.id ?: 0
            )
        }
        Row() {
            TextField(
                value = textState.value,
                onValueChange = { it: String ->
                    viewModel.setTextField(it)
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )
            Button(
                onClick = { viewModel.submit() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.submit_cta))
            }
        }
        Text(
            text = stringResource(R.string.name_length, pokemonToGuess.value?.name?.length ?: 0),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        AsyncImage(
            model = pokemonToGuess.value?.imageUrl,
            contentDescription = stringResource(id = R.string.guessing_image_content_description),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(150.dp)
                .height(150.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(
                items = viewModel.itemList
            ) { index, item ->
                ListItem(guessing = item, pokemonToGuess.value, viewModel.itemList.size - index)
            }
        }
    }
}