package sigsegv.com.health.api.entities

import java.text.SimpleDateFormat
import java.util.*

data class ViitaTime(
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
    val milliseconds: Int
)

fun CharSequence.toViitaTime(): ViitaTime {
    val dotSplitStr = this.split(".")
    val timeStr = dotSplitStr[0]
    val millisecondsStr = dotSplitStr[1]
    val colonSplitStr = timeStr.split(":")
    val hoursStr = colonSplitStr[0]
    val minutesStr = colonSplitStr[1]
    val secondsStr = colonSplitStr[2]

    val milliseconds = millisecondsStr.toInt()
    val hours = hoursStr.toInt()
    val minutes = minutesStr.toInt()
    val seconds = secondsStr.toInt()

    return ViitaTime(hours, minutes, seconds, milliseconds)
}

fun String.toDate(): Date = SimpleDateFormat("yyyy-MM-dd").parse(this)