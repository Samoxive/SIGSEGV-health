package sigsegv.com.health.api.entities

import java.util.*

// watchId: Long
//timestamp: DateTime
//activityType: ActivityType
//avgBpm: Int
//maxBpm: Int
//calories: Int
//water: Int
//duration: Long
//steps: Int
//distance: Int
//altitude: Int

data class ViitaActivityDataResponse(val watchId: Long,
                                     val timestamp: Long,
                                     val activityType: String,
                                     val avgBpm: Int,
                                     val maxBpm: Int,
                                     val calories: Int,
                                     val water: Int,
                                     val duration: Long,
                                     val steps: Int,
                                     val distance: Int,
                                     val altitude: Int
)