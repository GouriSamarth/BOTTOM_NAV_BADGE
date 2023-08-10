package com.example.bottomnavbar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomnavbar.ui.theme.BottomNavBarTheme
data class BottomNavItem(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badge:Int? = null
)
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavBarTheme {
                val items = listOf(
                    BottomNavItem(
                    title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),
                    BottomNavItem(
                        title = "Search",
                        selectedIcon = Icons.Filled.Search,
                        unselectedIcon = Icons.Outlined.Search,
                        hasNews = false
                    ),
                    BottomNavItem(
                        title = "Post",
                        selectedIcon = Icons.Filled.Add,
                        unselectedIcon = Icons.Outlined.Add,
                        hasNews = false,
                    ),
                    BottomNavItem(
                        title = "Message",
                        selectedIcon = Icons.Filled.MailOutline,
                        unselectedIcon = Icons.Outlined.MailOutline,
                        hasNews = false,
                        badge = 4
                    ),
                    BottomNavItem(
                        title = "Profile",
                        selectedIcon = Icons.Filled.Face,
                        unselectedIcon = Icons.Outlined.Face,
                        hasNews = true
                    ),
                    )
                // A surface container using the 'background' color from the theme
                var selectedItemIndex by rememberSaveable {
                   mutableStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        NavigationBar {
                            items.forEachIndexed{ index, item ->
                                NavigationBarItem(
                                selected = selectedItemIndex == index ,
                                onClick = { selectedItemIndex = index},
                                    label = {
                                            Text(text = item.title)
                                    },
                                    alwaysShowLabel = false,
                                icon = { BadgedBox(badge =
                                {
                                    if (item.badge!= null){
                                        Badge {
                                            Text(text = item.badge.toString()  )
                                        }

                                    }else if(item.hasNews){
                                        Badge()
                                    }
                                }) {
                                    Icon(imageVector = if(index == selectedItemIndex ){
                                        item.selectedIcon }
                                    else item.unselectedIcon,
                                        contentDescription = item.title)
                                }
                                }
                                )
                            }
                        }
                    }
                    )
                    {
                    }
                    }
                }
            }
        }
    }



