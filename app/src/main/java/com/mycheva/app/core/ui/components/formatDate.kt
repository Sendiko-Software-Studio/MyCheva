package com.mycheva.app.core.ui.components

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDateString(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(inputDate)

    return date?.let { outputFormat.format(it) } ?: "Invalid date"
}