package com.harissabil.glogslite.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harissabil.glogslite.ui.theme.GLogsLiteTheme
import com.harissabil.glogslite.ui.theme.Shapes

@Composable
fun GameItem(
    name: String,
    platforms: String?,
    image: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(0.dp, Color.Transparent),
                shape = Shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(text = platforms ?: "",
                style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Preview
@Composable
fun GameItemPreview() {
    GLogsLiteTheme {
        GameItem(
            name = "The Legend of Zelda: Breath of The Wild",
            platforms = null,
            image = null
        )
    }
}