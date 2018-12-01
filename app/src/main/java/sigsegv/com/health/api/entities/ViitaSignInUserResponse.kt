package sigsegv.com.health.api.entities

data class ViitaSignInUserResponse(
    val user: ViitaUserResponse,
    val device: ViitaRegisterDeviceResponse,
    val settings: ViitaCompositeSettings
)