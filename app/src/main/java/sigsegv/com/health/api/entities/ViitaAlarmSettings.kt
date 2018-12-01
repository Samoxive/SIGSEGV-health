package sigsegv.com.health.api.entities

data class ViitaAlarmSettings(
    val alarmEnabled: Boolean,
    val smartAlarmEnabled: Boolean,
    val mondayEnabled: Boolean,
    val mondayTime: String,
    val tuesdayEnabled: Boolean,
    val tuesdayTime: String,
    val wednesdayEnabled: Boolean,
    val wednesdayTime: String,
    val thursdayEnabled: Boolean,
    val thursdayTime: String,
    val fridayEnabled: Boolean,
    val fridayTime: String,
    val saturdayEnabled: Boolean,
    val saturdayTime: String,
    val sundayEnabled: Boolean,
    val sundayTime: String
)