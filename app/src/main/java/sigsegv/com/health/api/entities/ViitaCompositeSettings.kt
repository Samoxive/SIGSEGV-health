package sigsegv.com.health.api.entities

data class ViitaCompositeSettings(
    val accountSettings: ViitaAccountSettings,
    val alarmSettings: ViitaAlarmSettings,
    val userSettings: ViitaUserSettings,
    val watchSettings: ViitaWatchSettings
)