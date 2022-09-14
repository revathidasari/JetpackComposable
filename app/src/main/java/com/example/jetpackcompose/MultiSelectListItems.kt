package com.example.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectListItems() {
    var items by remember {
        mutableStateOf((1..20).map {
            MultiSelectListItemData(
                title = "Item $it",
                isSelected = false
            )
        })
    }

    //To get all the selected items
    //items.filter { it.isSelected }
    LazyColumn(modifier = Modifier
        .fillMaxSize()) {
      items(items.size) { index ->
          Row(modifier = Modifier
              .fillMaxWidth()
              .clickable {
                  items = items.mapIndexed { sindex, sitem ->
                      if (index == sindex) {
                          sitem.copy(isSelected = !sitem.isSelected)
                      } else {
                          sitem
                      }
                  }
              }
              .padding(20.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically) {
              Text(text = items[index].title)
              if (items[index].isSelected) {
                  Icon(imageVector = Icons.Default.Check,
                      contentDescription = "Selected",
                      tint = Color.Green,
                      modifier = Modifier.size(20.dp))
              }
          }
      }
    }
}

@Composable
fun ListingByScreenSize() {
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            items(10) {
                LazyListItems(text = "first item", color = Color.Green, count = 10)
                //one after other
               // LazyListItems(text = "second item", color = Color.Cyan, count = 5)
            }
            items(10) {
                 LazyListItems(text = "second item", color = Color.Cyan, count = 5)
            }
        }
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.weight(1f)
            ) {
                items(10) {
                    LazyListItems(text ="first1 ", color = Color.Magenta, count = 10)
                }
            }
            LazyColumn(modifier = Modifier.weight(1f)
            ) {
                items(6) {
                    LazyListItems(text ="second2 ", color = Color.Blue,count = 6)
                }
            }
        }
    }
}

@Composable
fun LazyListItems(
    text : String,
    color : Color,
    count : Int,
    modifier: Modifier = Modifier
) {
/*    Column(modifier = modifier) {
        for (i in 0..count) {*/
            Text(text = text , modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = color))
   /*     }
    }*/
}
