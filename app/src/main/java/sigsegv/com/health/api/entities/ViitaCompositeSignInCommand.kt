package sigsegv.com.health.api.entities

data class ViitaCompositeSignInCommand(val credentials: ViitaCredentials,
                                       val deviceCommand: ViitaRegisterDeviceCommand)