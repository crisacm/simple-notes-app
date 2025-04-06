package com.github.crisacm.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

object DateUtils {
  fun convertLongToDate(date: Long): String {
    val instant = Instant.fromEpochMilliseconds(date)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val dayOfWeek =
      dateTime.dayOfWeek.name
        .lowercase()
        .replaceFirstChar { it.uppercase() }
    val dayOfMonth = dateTime.dayOfMonth
    val month =
      dateTime.month.name
        .lowercase()
        .replaceFirstChar { it.uppercase() }
    return "$dayOfWeek, $dayOfMonth $month"
  }

  fun convertLongToDateWithConditional(date: Long): String {
    val instant = Instant.fromEpochMilliseconds(date)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    return when (dateTime.date) {
      currentDateTime.date -> {
        "Today"
      }

      currentDateTime.date.minus(1, DateTimeUnit.DAY) -> {
        "Yesterday"
      }

      else -> {
        val dayOfWeek =
          dateTime.dayOfWeek.name
            .lowercase()
            .replaceFirstChar { it.uppercase() }
        val dayOfMonth = dateTime.dayOfMonth
        val month =
          dateTime.month.name
            .lowercase()
            .replaceFirstChar { it.uppercase() }
        "$dayOfWeek, $dayOfMonth $month"
      }
    }
  }

  fun convertLongToTime(time: Long): String {
    val instant = Instant.fromEpochMilliseconds(time)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val hour = dateTime.hour % 12
    val formattedHour = if (hour == 0) 12 else hour
    val minute = dateTime.minute.toString().padStart(2, '0')
    val period = if (dateTime.hour < 12) "AM" else "PM"
    return "$formattedHour:$minute $period"
  }
}
