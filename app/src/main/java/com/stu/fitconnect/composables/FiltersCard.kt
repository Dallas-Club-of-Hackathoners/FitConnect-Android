package com.stu.fitconnect.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.stu.fitconnect.R
import com.stu.fitconnect.features.sportclubs.domain.entity.Filter
import com.stu.fitconnect.features.sportclubs.domain.entity.FilterCategory
import com.stu.fitconnect.ui.theme.BackgroundColor
import com.stu.fitconnect.ui.theme.Gray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FiltersCard(
    filterCategory: FilterCategory,
    onFilterSelected: (Filter) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = filterCategory.name,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
            for(filter in filterCategory.filtersList) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(
                            color = if(filter.isSelect) Color.White else Gray,
                            shape = CircleShape // Увеличьте радиус закругления по своему усмотрению
                        )
                        .clip(CircleShape)
                        .height(28.dp)
                        .clickable { onFilterSelected(filter) }
                ) {
                    Text(
                        text = filter.name,
                        color = if(filter.isSelect) Gray else Color.White,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 10.dp, start = 10.dp, bottom = 2.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
    
}

@Composable
fun FilterItem(filter: Filter) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(
                color = Gray,
                shape = CircleShape // Увеличьте радиус закругления по своему усмотрению
            )
            .clip(CircleShape)
            .height(28.dp)
    ) {
        Text(
            text = filter.name,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp, bottom = 2.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewFiltersCard() {
    val filters = mutableListOf(
        Filter(1, 1, "Душ", isSelect = true),
        Filter(2, 1, "хз еще", isSelect = false),
        Filter(3, 2, "длинное назание", isSelect = true),
        Filter(4, 2, "че то еще", isSelect = false),
        Filter(5, 3, "хз что", isSelect = true),
        Filter(6, 3, "хз что", isSelect = true),
        Filter(5, 3, "хз что", isSelect = true),
        Filter(6, 3, "хз что", isSelect = true),
        Filter(5, 3, "хз что", isSelect = false),
        Filter(6, 3, "хз что", isSelect = true),
        Filter(6, 3, "хз что", isSelect = true),
    )
    val category = FilterCategory(1, "Вид занятия", filters)
    Box(
        modifier = Modifier.fillMaxSize().background(color = BackgroundColor)
    ){
        FiltersCard(filterCategory = category, onFilterSelected = {})

    }
}