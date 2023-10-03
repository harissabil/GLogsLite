package com.harissabil.glogslite.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PlatformChip(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    onClick: (String) -> Unit = {},
    selectedItem: String
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(itemList.size) { item ->
                FilterChip(
                    modifier = Modifier,
                    selected = (itemList[item] == selectedItem),
                    onClick = {
                        onClick(itemList[item])
                    },
                    label = {
                        Text(text = itemList[item], style = MaterialTheme.typography.labelLarge)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.padding(end = 8.dp))
            }
        }
    }
}