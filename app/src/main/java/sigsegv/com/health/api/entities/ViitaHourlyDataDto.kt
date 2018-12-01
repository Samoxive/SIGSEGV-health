package sigsegv.com.health.api.entities

// watchId: Long
//date: LocalDate
//values: [Int] (max. 24 values; -1 means “no data available”)

data class ViitaHourlyDataDto (val watchId: Long, val date: String, val values: List<Int>)