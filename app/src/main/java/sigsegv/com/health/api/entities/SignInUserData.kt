package sigsegv.com.health.api.entities

data class SignInUserData(
    val token: String,
    val user: ViitaUserResponse,
    val device: ViitaRegisterDeviceResponse,
    val settings: CompositeSettings
)

fun ViitaSignInUserResponse.toSignInUserData() = SignInUserData(
    this.token,
    this.user,
    this.device,
    CompositeSettings(
        this.settings.accountSettings,
        this.settings.alarmSettings.toAlarmSettings(),
        this.settings.userSettings.toUserSettings(),
        this.settings.watchSettings.toWatchSettings()
    )
)