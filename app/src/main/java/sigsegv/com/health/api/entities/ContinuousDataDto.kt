package sigsegv.com.health.api.entities

import java.util.*

data class ContinuousDataDto(val watchId: Long, val date: Date, val values: List<Int>)

fun ViitaContinuousDataDto.toContinuousDataDto() = ContinuousDataDto(
    this.watchId,
    this.date.toDate(),
    this.values
)