package com.example.jetpackcompose.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.topBarColor
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.jetpackcompose.components.YearPickerButton
import com.example.jetpackcompose.components.DonutChartWithProgress
import com.example.jetpackcompose.components.ReportMonth
import com.example.jetpackcompose.ui.theme.highGray
import com.example.jetpackcompose.ui.theme.lightGray
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen() {
    val values = listOf(10, 20, 30, 25, 40, 35, 45, 50, 60, 55, 70, 65) // Dữ liệu cho các tháng
    val indexs = listOf(12, 22, 33, 24, 45, 36, 47, 58, 69, 50, 71, 62) // Mốc cho mỗi tháng
    val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    val currentYear = remember {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        "$year"
    }

    var selectedYear by remember { mutableStateOf(currentYear) }

    val customTypography = Typography(
        bodyLarge = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        bodyMedium = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        bodySmall = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        titleLarge = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        titleMedium = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        titleSmall = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        labelLarge = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        labelMedium = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        labelSmall = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        headlineLarge = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        headlineMedium = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat),
        headlineSmall = TextStyle(fontFamily = com.example.jetpackcompose.app.features.inputFeatures.montserrat)
    )

    MaterialTheme(typography = customTypography) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 16.dp, end = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Báo cáo",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                        },
                        navigationIcon = {},
                        actions = {},
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = topBarColor
                        ),
                        modifier = Modifier
                            .height(50.dp)
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                }
            }
        ) { paddingValues ->

            // Sử dụng LazyColumn để có thể cuộn được
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxHeight()
                    .background(color = lightGray)
            ) {
                item {
                    // Phần header
                    Column(
                        modifier = Modifier
                            .background(color = Color.White)
                    ) {
                        // Spacer giữa TopAppBar và biểu đồ
                        Spacer(modifier = Modifier.height(16.dp))

                        YearPickerButton(onYearSelected = { year ->
                            selectedYear = year
                        })

                        Spacer(modifier = Modifier.height(24.dp))

                        val values = listOf(40, 30, 20, 10)
                        val percents = listOf<Float>(0.8f, 0.3f, 0.2f, 0.7f)
                        val colors = listOf(
                            Color.Red,
                            Color.Blue,
                            Color.Green,
                            Color.Black
                        )
                        val labels = listOf("Red", "Blue", "Green", "Yellow")

                        DonutChartWithProgress(values, colors, labels, percents)

                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Thêm các phần tử khác vào LazyColumn
                item {
                    ReportMonth("Total", 10000000)
                    Spacer(modifier = Modifier.height(16.dp))
                }


                for(month in months) {
                    item {
                        ReportMonth(month, 100)
                        Divider(
                            color = highGray,
                            thickness = 1.dp
                        )
                    }
                }

            }
        }
    }
}



@Preview
@Composable
fun PreviewReportScreen() {
    MaterialTheme {
        ReportScreen()
    }
}
