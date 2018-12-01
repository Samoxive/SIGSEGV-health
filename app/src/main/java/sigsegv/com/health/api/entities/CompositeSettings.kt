package sigsegv.com.health.api.entities

data class CompositeSettings(
    val accountSettings: ViitaAccountSettings,
    val alarmSettings: AlarmSettings,
    val userSettings: UserSettings,
    val watchSettings: WatchSettings
)