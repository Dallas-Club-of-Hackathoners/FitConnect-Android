package com.stu.fitconnect.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.White

@Composable
fun AppOutlineButton(
    onClick: () -> Unit,
    textValue: String,
    textStile: TextStyle,
    modifier: Modifier
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, Gray),
        shape = RoundedCornerShape(18.dp),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Text(
            text = textValue,
            style = textStile
        )

    }
}

@Preview
@Composable
fun PreviewAppOutlineButton() {
    FitConnectTheme {
        AppOutlineButton(
            onClick = {},
            textValue = "Рекомендуем",
            textStile = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.height(27.dp))
    }
}