package com.stu.fitconnect.test

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TestScreen(
    viewModel: TestViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    Log.v("TAG TEST VIEW MODEL", viewModel.hashCode().toString())
    //val state = viewModel.state.collectAsState()
    Column {
        Text(text = "state.value.textValue")
        Button(onClick = onNavigate) {

        }
    }
}