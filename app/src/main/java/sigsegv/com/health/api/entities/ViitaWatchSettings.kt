package sigsegv.com.health.api.entities

data class ViitaWatchSettings(
    val notificationsEnabled: Boolean,
    val notificationsActivityEnabled: Boolean,
    val notificationTimeEnabled: Boolean,
    val notificationTimeStart: String,
    val notificationTimeEnd: String,
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

data class ViitaNotificationTypes(
    val incomingCall: Boolean,
    val textMessage: Boolean,
    val whatsapp: Boolean,
    val email: Boolean,
    val skype: Boolean,
    val calendar: Boolean,
    val facebook: Boolean,
    val facebookMessenger: Boolean,
    val snapchat: Boolean,
    val wechat: Boolean,
    val qq: Boolean
)