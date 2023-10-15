package com.stu.fitconnect.ui

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stu.fitconnect.R
import com.stu.fitconnect.features.authentication.domain.AuthField
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String = "",
    modifier: Modifier = Modifier,
    fieldType: AuthField = AuthField.Email
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .defaultMinSize(minHeight = 47.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 1.dp, Gray),
                shape = RoundedCornerShape(17.dp)
            )
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = BackgroundColor)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        textStyle = LocalTextStyle.current.copy(
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            fontSize = 14.sp),
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        visualTransformation = if (fieldType == AuthField.Password || fieldType == AuthField.RepeatPassword) PasswordVisualTransformation()
            else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if(fieldType == AuthField.Email) KeyboardType.Email
                else if(fieldType == AuthField.Password || fieldType == AuthField.RepeatPassword) KeyboardType.Password
                else KeyboardType.Text,
            autoCorrect = false),
        decorationBox = { innerTextField ->
            if(value == "")
                Text(
                    text = label,
                    color = Gray,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            innerTextField()
        }
    )
}

@Preview
@Composable
fun AuthTextFieldPreview() {
    Column {
        AuthTextField(label = "label")
        AuthTextField(value = "value")
    }
}