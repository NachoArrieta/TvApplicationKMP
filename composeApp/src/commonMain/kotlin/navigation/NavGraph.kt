package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import tvapplicationkmp.composeapp.generated.resources.Res
import tvapplicationkmp.composeapp.generated.resources.app_title
import tvapplicationkmp.composeapp.generated.resources.artist_detail
import tvapplicationkmp.composeapp.generated.resources.movie_detail
import tvapplicationkmp.composeapp.generated.resources.tv_series_detail
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import org.jetbrains.compose.resources.stringResource
import ui.artist_detail.ArtistDetail
import ui.celebrities.popular.PopularCelebrities
import ui.celebrities.trending.TrendingCelebrities
import ui.movie.detail.MovieDetail
import ui.movie.now_playing.NowPlayingScreen
import ui.movie.popular.PopularMovie
import ui.movie.top_rated.TopRatedMovie
import ui.movie.upcoming.UpcomingMovie
import ui.tv_series.airing_today.AiringTodayTvSeries
import ui.tv_series.detail.TvSeriesDetail
import ui.tv_series.on_the_air.OnTheAirTvSeries
import ui.tv_series.popular.PopularTvSeries
import ui.tv_series.top_rated.TopRatedTvSeries

@Composable
fun Navigation(navigator: Navigator, page: Int) {
    NavHost(
        navigator = navigator,
        initialRoute = initialScreen(page),
    ) {
        scene(route = NavigationScreen.NowPlayingMovie.route) {
            NowPlayingScreen(navigator)
        }
        scene(route = NavigationScreen.PopularMovie.route) {
            PopularMovie(navigator)
        }
        scene(route = NavigationScreen.TopRatedMovie.route) {
            TopRatedMovie(navigator)
        }
        scene(route = NavigationScreen.UpcomingMovie.route) {
            UpcomingMovie(navigator)
        }
        scene(route = NavigationScreen.MovieDetail.route.plus(NavigationScreen.MovieDetail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(NavigationScreen.MovieDetail.objectName)
            id?.let {
                MovieDetail(navigator, it)
            }
        }
        scene(route = NavigationScreen.ArtistDetail.route.plus(NavigationScreen.ArtistDetail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(NavigationScreen.ArtistDetail.objectName)
            id?.let {
                ArtistDetail(navigator, it)
            }
        }
        scene(route = NavigationScreen.AiringTodayTvSeries.route) {
            AiringTodayTvSeries(navigator)
        }
        scene(route = NavigationScreen.OnTheAirTvSeries.route) {
            OnTheAirTvSeries(navigator)
        }
        scene(route = NavigationScreen.PopularTvSeries.route) {
            PopularTvSeries(navigator)
        }
        scene(route = NavigationScreen.TopRatedTvSeries.route) {
            TopRatedTvSeries(navigator)
        }
        scene(route = NavigationScreen.TvSeriesDetail.route.plus(NavigationScreen.TvSeriesDetail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(NavigationScreen.TvSeriesDetail.objectName)
            id?.let {
                TvSeriesDetail(navigator, it)
            }
        }
        scene(route = NavigationScreen.PopularCelebrity.route) {
            PopularCelebrities(navigator)
        }
        scene(route = NavigationScreen.TrendingCelebrity.route) {
            TrendingCelebrities(navigator)
        }
    }
}

@Composable
fun navigationTitle(navController: Navigator): String {
    return when (currentRoute(navController)) {
        NavigationScreen.MovieDetail.route -> stringResource(Res.string.movie_detail)
        NavigationScreen.ArtistDetail.route -> stringResource(Res.string.artist_detail)
        NavigationScreen.TvSeriesDetail.route -> stringResource(Res.string.tv_series_detail)
        else -> {
            stringResource(Res.string.app_title)
        }
    }
}

@Composable
fun currentRoute(navigator: Navigator): String? {
    val routeName = navigator.currentEntry.collectAsState(null).value?.route?.route
    routeName?.let {
        return it.substringBefore("/")
    } ?: run {
        return ""
    }
}

fun initialScreen(page: Int): String {
    return when (page) {
        0 -> {
            NavigationScreen.NowPlayingMovie.route
        }

        1 -> {
            NavigationScreen.AiringTodayTvSeries.route
        }

        else -> {
            NavigationScreen.PopularCelebrity.route
        }
    }
}

@Composable
fun isBottomBarVisible(navigator: Navigator): Boolean {
    val visibleRoutes = setOf(
        NavigationScreen.NowPlayingMovie.route,
        NavigationScreen.PopularMovie.route,
        NavigationScreen.TopRatedMovie.route,
        NavigationScreen.UpcomingMovie.route,
        NavigationScreen.AiringTodayTvSeries.route,
        NavigationScreen.OnTheAirTvSeries.route,
        NavigationScreen.PopularTvSeries.route,
        NavigationScreen.TopRatedTvSeries.route,
        NavigationScreen.TrendingCelebrity.route,
        NavigationScreen.PopularCelebrity.route
    )

    return currentRoute(navigator) in visibleRoutes
}