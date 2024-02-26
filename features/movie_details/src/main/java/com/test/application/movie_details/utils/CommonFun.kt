package com.test.application.movie_details.utils

import android.content.Context
import androidx.viewpager2.widget.ViewPager2
import com.test.application.core.domain.movieDetail.Budget
import com.test.application.movie_details.R
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun extractVideoIdFromUrl(videoUrl: String): String {
    val regex = Regex("/embed/(\\S+)")
    val match = regex.find(videoUrl)
    return match?.groups?.get(1)?.value ?: ""
}

fun reformatPremiereDate(context: Context, dateStr: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")
        val date = LocalDate.parse(dateStr, formatter)
        date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault()))
    } catch(e: DateTimeParseException) {
        context.getString(R.string.undefined_date)
    }
}

fun reformatDate(context: Context, dateStr: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")
        val date = LocalDate.parse(dateStr, formatter)
        date.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault()))
    } catch(e: DateTimeParseException) {
        context.getString(R.string.undefined_date)
    }
}

fun reformatBudget(context: Context, budget: Budget?): String {
    return if(budget != null && budget.value > 0) {
        val formattedValue = NumberFormat
            .getNumberInstance(Locale.getDefault()).format(budget.value)
        "$formattedValue ${budget.currency}"
    } else context.getString(R.string.undefined_budget)
}

fun reformatVotes(votes: Int?): String {
    return if(votes != null) {
        val thousands = votes / 1000.0
        String.format("%,.1fK", thousands)
    } else {
        "0.0"
    }
}

fun ViewPager2.disableSwipe() {
    this.isUserInputEnabled = false
}