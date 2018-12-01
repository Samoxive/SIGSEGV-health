package sigsegv.com.health.api.entities

data class AlarmSettings(
    val alarmEnabled: Boolean,
    val smartAlarmEnabled: Boolean,
    val mondayEnabled: Boolean,
    val mondayTime: ViitaTime,
    val tuesdayEnabled: Boolean,
    val tuesdayTime: ViitaTime,
    val wednesdayEnabled: Boolean,
    val wednesdayTime: ViitaTime,
    val thursdayEnabled: Boolean,
    val thursdayTime: ViitaTime,
    val fridayEnabled: Boolean,
    val fridayTime: ViitaTime,
    val saturdayEnabled: Boolean,
    val saturdayTime: ViitaTime,
    val sundayEnabled: Boolean,
    val sundayTime: ViitaTime
)

fun ViitaAlarmSettings.toAlarmSettings() = AlarmSettings(
    this.alarmEnabled,
    this.smartAlarmEnabled,
    this.mondayEnabled,
    this.mondayTime.toViitaTime(),
    this.tuesdayEnabled,
    this.tuesdayTime.toViitaTime(),
    this.wednesdayEnabled,
    this.wednesdayTime.toViitaTime(),
    this.thursdayEnabled,
    this.thursdayTime.toViitaTime(),
    this.fridayEnabled,
    this.fridayTime.toViitaTime(),
    this.saturdayEnabled,
    this.saturdayTime.toViitaTime(),
    this.sundayEnabled,
    this.sundayTime.toViitaTime()
)
