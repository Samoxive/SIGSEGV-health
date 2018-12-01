package sigsegv.com.health.api.entities

data class ViitaSignInUserResponse(
    val token: String,
    val user: ViitaUserResponse,
    val device: ViitaRegisterDeviceResponse,
    val settings: ViitaCompositeSettings
)