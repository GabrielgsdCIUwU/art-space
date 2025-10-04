package edu.estatuas.art

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.estatuas.art.ui.theme.ArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainStructure(
                        modifier = Modifier.padding(innerPadding).wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun MainStructure(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(R.drawable.monalisa, R.string.p_mona_lisa, R.string.a_leonardo),
        Artwork(R.drawable.starrynight, R.string.p_starry_night, R.string.a_vincent),
        Artwork(R.drawable.girl, R.string.p_girl, R.string.a_johannes)
    )

    var index by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[index]

    Column (
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true),

            contentAlignment = Alignment.Center
        ) {
            ArtworkDisplay(
                painterResource(currentArtwork.imageRes),
                stringResource(currentArtwork.titleRes),
                stringResource(currentArtwork.authorRes),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        NavigationButtons(
            onPrevious = { if (index > 0) index--},
            onNext = {if (index < artworks.lastIndex) index++},
            modifier
        )
    }
}

@Composable
fun ArtworkDisplay(
    image: Painter,
    title: String,
    author: String,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                image,
                title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(title, style = MaterialTheme.typography.titleLarge)
        Text(author, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun NavigationButtons(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        Button(
            onClick = onPrevious,
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(R.string.previous))

        }
        Spacer(modifier = modifier.width(25.dp))
        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(R.string.next))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ShowcasePreview() {
    ArtTheme {
        MainStructure()
    }
}