package sigsegv.com.health.api.entities

// watchId: Long
//date: LocalDate
//values: [Int] (max. 24 x 6 values; -1 means “no data available”)

data class ViitaContinuousDataDto (val watchId: Long, val date: String, val values: List<Int>)