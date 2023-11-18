package com.stu.fitconnect.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.FitConnectTheme
import com.stu.fitconnect.ui.theme.Gray
import com.stu.fitconnect.ui.theme.White

@Composable
fun AuthTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    fieldType: AuthField = AuthField.Email
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .defaultMinSize(minHeight = 47.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 1.dp, Gray),
                shape = RoundedCornerShape(17.dp)
            )
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = BackgroundColor)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        textStyle = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(White),
        visualTransformation =
            if (fieldType == AuthField.Password || fieldType == AuthField.RepeatPassword)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType =
            when (fieldType) {
                AuthField.Email -> KeyboardType.Email
                AuthField.Password, AuthField.RepeatPassword -> KeyboardType.Password
                else -> KeyboardType.Text
            },
            autoCorrect = false),
        decorationBox = { innerTextField ->
            if(value.isEmpty())
                innerTextField()
                Text(
                    text = label,
                    color = Gray,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
        }
    )
}

@Preview
@Composable
fun AuthTextFieldPreview() {
    FitConnectTheme {
        Column {
            AuthTextField(label = "label")
            AuthTextField(value = "value")
        }
    }
}