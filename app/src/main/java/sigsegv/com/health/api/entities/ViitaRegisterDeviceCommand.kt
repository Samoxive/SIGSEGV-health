package sigsegv.com.health.api.entities

data class ViitaRegisterDeviceCommand(
    val deviceId: Long?,
    val deviceType: String,
    val osVersion: String,
    val appVersion: String,
    val model: String,
    val pushToken: String?,
    val language: String
)