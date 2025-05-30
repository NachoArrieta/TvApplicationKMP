package ui.movie.now_playing

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import component.Movies
import component.base.BaseColumn
import moe.tlaster.precompose.navigation.Navigator
import navigation.NavigationScreen
import utils.OnGridPagination

@Composable
fun NowPlayingScreen(
    navigator: Navigator,
    viewModel: NowPlayingViewModel = androidx.lifecycle.viewmodel.compose.viewModel { NowPlayingViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit){
        viewModel.loadNowPlayingMovies()
    }

    BaseColumn(
        loading = uiState.isLoading,
        errorMessage = uiState.errorMessage
    ) {
        uiState.movieList?.let {
            Movies(it, gridState) { movieId ->
                navigator.navigate(NavigationScreen.MovieDetail.route.plus("/$movieId"))
            }
        }
        OnGridPagination(gridState = gridState) {
            viewModel.loadNowPlayingMovies()
        }
    }
}