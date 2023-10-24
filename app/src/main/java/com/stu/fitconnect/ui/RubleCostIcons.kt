package com.stu.fitconnect.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.stu.fitconnect.R
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.White

@Composable
fun RubleCostIcons(
    cost: Int,
    iconSize: Dp = 26.0.dp
) {
    Box(
        modifier = Modifier.width(1.5 * iconSize)
    ) {
        repeat(3) { position ->
            Icon(
                painter = painterResource(id = R.drawable.ruble),
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize)
                    .offset(x = when (position) {
                        0 -> -(2 * iconSize.value.toDouble() / 9).dp
                        1 -> (2 * iconSize.value.toDouble() / 9).dp
                        else -> (6 * iconSize.value.toDouble() / 9).dp
                    }),
                tint = if (position < cost) White else Gray
            )
        }
    }
}

@Preview
@Composable
fun PreviewRubleCostIcons() {
    FitConnectTheme {
        Column {
            RubleCostIcons(cost = 0, iconSize = 11.dp)
            RubleCostIcons(cost = 1)
            RubleCostIcons(cost = 2)
            RubleCostIcons(cost = 3, iconSize = 49.dp)
            RubleCostIcons(cost = 4, iconSize = 50.dp)
        }
    }
}