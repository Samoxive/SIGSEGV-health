package sigsegv.com.health.api.entities

data class UserData(
    val activities: List<ViitaActivityDataResponse>,
    val steps: List<HourlyDataDto>,
    val calories: List<HourlyDataDto>,
    val water: List<HourlyDataDto>,
    val regeneration: List<ContinuousDataDto>,
    val stress: List<ContinuousDataDto>,
    val sleep: List<ViitaSleepDataDto>
)

fun ViitaFullDataResponse.toUserData() = UserData(
    this.activities,
    this.steps.map { it.toHourlyDataDto() },
    this.calories.map { it.toHourlyDataDto() },
    this.water.map { it.toHourlyDataDto() },
    this.regeneration.map { it.toContinuousDataDto() },
    this.stress.map { it.toContinuousDataDto() },
    this.sleep
)