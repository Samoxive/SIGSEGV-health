package sigsegv.com.health.api

import android.content.Context
import com.google.gson.Gson
import com.snappydb.DB
import com.snappydb.DBFactory
import sigsegv.com.health.api.entities.SignInUserData
import sigsegv.com.health.api.entities.UserData
import sigsegv.com.health.api.entities.toSignInUserData
import sigsegv.com.health.api.entities.toUserData

val mockUsers = mapOf(
    "utanis@viita-concept.com" to "12345678",
    "mtarhan@viita-concept.com" to "12345678",
    "akoroglu@viita-concept.com" to "12345678",
    "scomert@viita-concept.com" to "12345678",
    "ecoskun1@viita-concept.com" to "12345678",
    "esakut@viita-concept.com" to "12345678",
    "ddincer@viita-concept.com" to "12345678",
    "hkocak1@viita-concept.com" to "12345678"
)

private val gson = Gson()

private suspend fun <T> Context.withDatabase(block: (DB) -> T): T {
    val db = DBFactory.open(this)
    val value = block(db)
    db.close()
    return value
}

private suspend fun getSignInDataFromStore(context: Context, email: String): SignInUserData? {
    val getValue: String? = context.withDatabase { it.get(email) } ?: return null
    return gson.fromJson(getValue, SignInUserData::class.java)
}

private suspend fun putSignInDataIntoStore(context: Context, data: SignInUserData) {
    context.withDatabase { it.put(data.user.email, gson.toJson(data)) }
}

suspend fun getAllUsers(context: Context): List<SignInUserData> = mockUsers.map { getUser(context, it.key) }

suspend fun getUser(context: Context, email: String): SignInUserData {
    val userFromStore = getSignInDataFromStore(context, email)

    return if (userFromStore == null) {
        val userData = registerUserApi(email, mockUsers[email]!!).toSignInUserData()
        putSignInDataIntoStore(context, userData)
        userData
    } else {
        userFromStore
    }
}

suspend fun getUserData(signInData: SignInUserData): UserData = fetchUserData(signInData.token).toUserData()
suspend fun getUserData(context: Context, email: String) = getUserData(getUser(context, email))
