package com.hezd.httpclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hezd.httpclient.ui.theme.HttpClientTheme
import com.hezd.httpclient.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserRepos()
                }
            }
        }
    }
}

@Composable
fun UserRepos(modifier: Modifier = Modifier, mainViewModel: MainViewModel = viewModel()) {
    val repos by mainViewModel.userInfo.collectAsState()
    val size = repos.repos.size
    val info: String =
        if (size > 0) {
            repos.repos[0].name
        } else {
            "empty"
        }
    Text(
        text = info,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HttpClientTheme {
        Greeting("Android")
    }
}