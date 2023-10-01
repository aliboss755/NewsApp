package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.loc.newsapp.domin.usercases.appentry.AppEntryUsesCases
import com.loc.newsapp.presention.home.HomeScreen
import com.loc.newsapp.presention.home.HomeViewModel
import com.loc.newsapp.presention.onbarding.OnBoardingScreen
import com.loc.newsapp.presention.onbarding.OnBoardingViewModel
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.plcoding.stockmarketapp.destinations.PostScreenDestination
import com.plcoding.stockmarketapp.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var  appEntryUsesCases: AppEntryUsesCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(
            window,false
        )
        installSplashScreen().apply {



        }
        lifecycleScope.launch {
            appEntryUsesCases.readAppEntry().collect{
                Log.d("ali",  it.toString())
            }
        }
        setContent {
            NewsAppTheme {

                val isSystemDark = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDark
                    )
                }
                Surface {
                    val homeViewModel :HomeViewModel = hiltViewModel()
                    val articles =homeViewModel.news.collectAsLazyPagingItems()
                    HomeScreen( articles =articles)


                    val viewModel : OnBoardingViewModel = hiltViewModel()
//                    OnBoardingScreen(
//                        event = viewModel::onEvent,
//                        null
//
//                    )
                }

            }
        }
    }


}
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navigator.navigate(
                ProfileScreenDestination("name")
            )
        }) {
            Text("Go to Profile Screen")
        }
    }
}
@Destination(route = "")
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    name: String = ""
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $name", textAlign = TextAlign.Center)
        Button(onClick = {
            navigator.navigate(
                PostScreenDestination(),
                true
            )
        }) {
            Text("Go to Post Screen")
        }
    }
}


@Destination
@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}

