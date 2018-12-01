package sigsegv.com.health.api.entities

// activities: [ActivityDataResponse]
//steps: [HourlyDataDto] (Saatte 1 veri çekiliyor, atılan adım sayısını ölçüyor)
//calories: [HourlyDataDto] (Saatte 1 veri çekiliyor, yakılan kaloriyi ölçüyor)
//water: [HourlyDataDto]
//regeneration: [ContinuousDataDto] (10 dakikada 1 veri çekiliyor, yenilenme seviyesini ölçüyor)
//stress: [ContinuousDataDto] (10 dakikada 1 veri çekiliyor, stress seviyesini ölçüyor)
//sleep: [SleepDataDto]

data class ViitaFullDataResponse(
    val activities: List<ViitaActivityDataResponse>,
    val steps: List<ViitaHourlyDataDto>,
    val calories: List<ViitaHourlyDataDto>,
    val water: List<ViitaHourlyDataDto>,
    val regeneration: List<ViitaContinuousDataDto>,
    val stress: List<ViitaContinuousDataDto>,
    val sleep: List<ViitaSleepDataDto>
)