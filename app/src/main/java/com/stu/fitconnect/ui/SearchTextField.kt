package com.stu.fitconnect.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 1.dp, Gray),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = BackgroundColor),
        textStyle = MaterialTheme.typography.headlineSmall,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Gray
                )
                if (value.isEmpty()) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun PreviewSearchTextField() {
    FitConnectTheme {
        Column {
            SearchTextField(value = "", onValueChange = {}, label = "Поиск спортзала")
            SearchTextField(value = "Fitnessss", onValueChange = {}, label = "")
        }
    }
}