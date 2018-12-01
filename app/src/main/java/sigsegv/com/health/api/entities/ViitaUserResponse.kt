package sigsegv.com.health.api.entities

data class ViitaUserResponse(val id: Long,
                             val verified: Boolean,
                             val email: String,
                             val avatarUrl: String?)