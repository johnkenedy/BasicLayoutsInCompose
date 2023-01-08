package com.example.basiclayoutsincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutsincompose.ui.theme.BasicLayoutsInComposeTheme
import com.example.basiclayoutsincompose.ui.theme.SurfaceColor
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutsInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SurfaceColor
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

private val dogs = listOf(
    R.drawable.dog1 to R.string.dog1,
    R.drawable.dog2 to R.string.dog2,
    R.drawable.dog3 to R.string.dog3,
    R.drawable.dog4 to R.string.dog4,
    R.drawable.dog5 to R.string.dog5,
    R.drawable.dog6 to R.string.dog6,
).map { DrawableStringPair(it.first, it.second) }

private val cats = listOf(
    R.drawable.cat1 to R.string.cat1,
    R.drawable.cat2 to R.string.cat2,
    R.drawable.cat3 to R.string.cat3,
    R.drawable.cat4 to R.string.cat4,
    R.drawable.cat5 to R.string.cat5,
    R.drawable.cat6 to R.string.cat6,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(value = "", onValueChange = {}, leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null)
    }, placeholder = {
        Text(text = stringResource(id = R.string.placeholder_search))
    }, modifier = modifier
        .heightIn(min = 56.dp)
        .fillMaxWidth()
    )
}

@Composable
fun DogElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(text),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun CatsElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .width(192.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun DogsRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dogs) { dog ->
            DogElement(
                drawable = dog.drawable,
                text = dog.text
            )
        }
    }
}

@Composable
fun CatsRow(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(cats) { cat ->
            CatsElement(
                drawable = cat.drawable,
                text = cat.text
            )
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.dogs) {
            DogsRow()
        }
        HomeSection(title = R.string.cats) {
            CatsRow()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    SearchBar(Modifier.padding(8.dp))

}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun DogElementPreview() {
    DogElement(
        drawable = R.drawable.dog1,
        text = R.string.dog1
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CatsCardPreview() {
    CatsElement(
        modifier = Modifier.padding(10.dp),
        drawable = R.drawable.cat1,
        text = R.string.cat1
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun DogsRowPreview() {
    DogsRow(modifier = Modifier.padding(10.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CatsRowPreview() {
    CatsRow(modifier = Modifier.padding(10.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    HomeSection(title = R.string.dogs) {
        DogsRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}