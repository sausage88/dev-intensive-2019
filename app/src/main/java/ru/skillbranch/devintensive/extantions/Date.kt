package ru.skillbranch.devintensive.extantions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val timeDiff = date.time - this.time

    fun getDaysUnit(days: Long): String {
        return when {
            days.toString().takeLast(1).toInt() == 1 -> "день"
            days in (5..20) -> "дней"
            days.toString().takeLast(1).toInt() in (2..4) -> "дня"
            else -> "дней"
        }
    }

    fun getHoursUnit(hours: Long): String {
        return  when (hours.toInt()) {
            1, 21 -> "час"
            in (2..4), in (22..24) -> "часа"
            else -> "часов"
        }
    }

    fun getMinutesUnit(minutes: Long): String {
        return  when {
            minutes.toString().takeLast(1).toInt() == 1 -> "минуту"
            minutes in (5..20) -> "минут"
            minutes.toString().takeLast(1).toInt() in (2..4) -> "минуты"
            else -> "минут"
        }
    }

    return when {
        timeDiff > 0 -> {
            when {
                timeDiff in (0..SECOND) -> {
                    "только что"
                }
                timeDiff < 45 * SECOND -> {
                    "несколько секунд назад"
                }
                timeDiff < 75 * SECOND -> {
                    "минуту назад"
                }
                timeDiff < 45 * MINUTE -> {
                    val minutes = timeDiff / MINUTE
                    val unit = getMinutesUnit(minutes)
                    "$minutes $unit назад"
                }
                timeDiff < 75 * MINUTE -> {
                    "час назад"
                }
                timeDiff < 22 * HOUR -> {
                    val hours = timeDiff / HOUR
                    val unit = getHoursUnit(hours)
                    "$hours $unit назад"
                }
                timeDiff < 26 * HOUR -> {
                    "день назад"
                }
                timeDiff < 360 * DAY -> {
                    val days = timeDiff / DAY
                    val unit = getDaysUnit(days)
                    "$days $unit назад"
                }
                else -> {
                    "более года назад"
                }
            }
        }
        else -> {
            when {
                -timeDiff < 45 * SECOND -> {
                    "через несколько секунд"
                }
                -timeDiff < 75 * SECOND -> {
                    "через минуту"
                }
                -timeDiff < 45 * MINUTE -> {
                    val minutes = -timeDiff / MINUTE
                    val unit = getMinutesUnit(minutes)
                    "через $minutes $unit"
                }
                -timeDiff < 75 * MINUTE -> {
                    "через час"
                }
                -timeDiff < 22 * HOUR -> {
                    val hours = -timeDiff / HOUR
                    val unit = getHoursUnit(hours)
                    "через $hours $unit"
                }
                -timeDiff < 26 * HOUR -> {
                    "Через день"
                }
                -timeDiff < 360 * DAY -> {
                    val days = -timeDiff / DAY
                    val unit = getDaysUnit(days)
                    "через $days $unit"
                }
                else -> {
                    "более чем через год"
                }
            }
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}