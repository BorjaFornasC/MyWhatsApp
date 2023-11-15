package com.example.mywhatsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mywhatsapp.ui.theme.MyWhatsAppTheme
import com.example.mywhatsapp.ui.theme.WhatsNormal
import com.example.mywhatsapp.ui.theme.WhatsOscuro
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWhatsAppTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    rememberTopAppBarState())
                // A surface container using the 'background' color from the theme
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                    floatingActionButton = { MyFloatingActionButton() },
                    topBar = {MyTopAppBar(scrollBehavior)},
                    containerColor = MaterialTheme.colorScheme.background)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = it.calculateTopPadding())
                    ) {
                        MyTabs()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior){
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(title = { Text(text = "MyWhatsApp", color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = WhatsNormal),
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color.White
                )
            }
        }, scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs() {

    val scope = rememberCoroutineScope()
    val titles = listOf("Chats", "Novedad", "Llamadas", "Estrella")
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        titles.size
    }
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = WhatsNormal,
            contentColor = Color.White
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(page = index) } },
                    text = { Text(text = title) }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> chats()
                1 -> novedad()
                2 -> llamadas()
                3 -> estrella()
            }
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }, containerColor = WhatsOscuro, modifier = Modifier.size(60.dp),
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()) {
        val image =
            AnimatedImageVector.animatedVectorResource(R.drawable.icono_animado
            )
        var atEnd by remember { mutableStateOf(false) }
        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "VectorDrawable",
            modifier = Modifier
                .clickable {
                    atEnd = !atEnd
                }
                .size(32.dp),
            contentScale = ContentScale.Crop,
        )
    }
}
