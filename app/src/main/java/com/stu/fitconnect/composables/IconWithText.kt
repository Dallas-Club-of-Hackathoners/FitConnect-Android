package com.stu.fitconnect.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stu.fitconnect.R
import com.stu.fitconnect.ui.theme.DimGreen
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Green
import com.stu.fitconnect.ui.theme.TextGray

@Composable
fun IconWithText(
    textValue: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    iconRes: Int,
    iconColor: Color = Green,
    textDecoration: TextDecoration? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .scale(scaleX = 1.2f, scaleY = 1.2f),
            colorFilter = ColorFilter.tint(iconColor),
            contentScale = ContentScale.Crop
        )
        Text(
            text = textValue,
            style = textStyle,
            textDecoration = textDecoration,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun PreviewIconWithText() {
    FitConnectTheme {
        Column {
            IconWithText(textValue = "Кафе", iconRes = R.drawable.cafe)
            IconWithText(
                textValue = "Кафе",
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = TextGray),
                textDecoration = TextDecoration.LineThrough,
                iconColor = DimGreen,
                iconRes = R.drawable.cafe)
            IconWithText(textValue = "Площадь ленина", textStyle = MaterialTheme.typography.headlineSmall, iconRes = R.drawable.metro)
            IconWithText(textValue = "Василеостровская", textStyle = MaterialTheme.typography.bodyMedium.copy(color = Green), iconRes = R.drawable.metro)
        }
    }
}