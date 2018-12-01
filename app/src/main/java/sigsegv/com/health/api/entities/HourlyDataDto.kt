package sigsegv.com.health.api.entities

import java.util.*

data class HourlyDataDto(val watchId: Long, val date: Date, val values: List<Int>)

fun ViitaHourlyDataDto.toHourlyDataDto() = HourlyDataDto(
    this.watchId,
    this.date.toDate(),
    this.values
)