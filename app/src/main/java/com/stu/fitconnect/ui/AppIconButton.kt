package com.stu.fitconnect.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.Green

@Composable
fun AppIconButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    OutlinedButton(
        contentPadding = PaddingValues(),
        border = BorderStroke(1.dp, Gray),
        modifier = Modifier
            .height(27.dp)
            .width(35.dp),
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
    ) {
        Icon(
            modifier = Modifier.size(19.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Green
        )
    }
}

@Preview
@Composable
fun PreviewAppIconButton() {
    AppIconButton(onClick = {}, icon = Icons.Default.FavoriteBorder)
}