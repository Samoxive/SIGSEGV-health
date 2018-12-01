package sigsegv.com.health.api.entities

data class WatchSettings(
    val notificationsEnabled: Boolean,
    val notificationsActivityEnabled: Boolean,
    val notificationTimeEnabled: Boolean,
    val notificationTimeStart: ViitaTime,
    val notificationTimeEnd: ViitaTime,
    val notificationTypes: ViitaNotificationTypes,
    val timeFormat: String,
    val autoScreenEnabled: Boolean,
    val flowHrvEnabled: Boolean,
    val favoriteActivities: List<String>,
    val favoriteHomeScreen: String,
    val favoriteScreenDesign: String,
    val activitySetBreak: Int,
    val activitySetExercise: Int
)

fun ViitaWatchSettings.toWatchSettings() = WatchSettings(
    this.notificationsEnabled,
    this.notificationsActivityEnabled,
    this.notificationTimeEnabled,
    this.notificationTimeStart.toViitaTime(),
    this.notificationTimeEnd.toViitaTime(),
    this.notificationTypes,
    this.timeFormat,
    this.autoScreenEnabled,
    this.flowHrvEnabled,
    this.favoriteActivities,
    this.favoriteHomeScreen,
    this.favoriteScreenDesign,
    this.activitySetBreak,
    this.activitySetExercise
)