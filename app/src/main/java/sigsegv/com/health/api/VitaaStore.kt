package sigsegv.com.health.api

import android.content.Context
import com.google.gson.Gson
import com.snappydb.DB
import com.snappydb.DBFactory
import sigsegv.com.health.api.entities.SignInUserData
import sigsegv.com.health.api.entities.UserData
import sigsegv.com.health.api.entities.toSignInUserData
import sigsegv.com.health.api.entities.toUserData
import java.lang.Exception

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

@Synchronized
private fun <T> Context.withDatabase(block: (DB) -> T): T? {
    val db = DBFactory.open(this)
    val value = try {
        block(db)
    } catch (e: Exception) {
        null
    }
    db.close()
    return value
}

private fun getSignInDataFromStore(context: Context, email: String): SignInUserData? {
    val getValue: String? = context.withDatabase { it.get(email) } ?: return null
    return gson.fromJson(getValue, SignInUserData::class.java)
}

private fun putSignInDataIntoStore(context: Context, data: SignInUserData) {
    context.withDatabase { it.put(data.user.email, gson.toJson(data)) }
}

fun getAllUsers(context: Context): List<SignInUserData> = mockUsers.map { getUser(context, it.key) }

fun getUser(context: Context, email: String): SignInUserData {
    val userFromStore = getSignInDataFromStore(context, email)

    return if (userFromStore == null) {
        val userData = registerUserApi(email, mockUsers[email]!!).toSignInUserData()
        putSignInDataIntoStore(context, userData)
        userData
    } else {
        userFromStore
    }
}

fun getUserData(signInData: SignInUserData): UserData = fetchUserData(signInData.token).toUserData()
fun getUserData(context: Context, email: String) = getUserData(getUser(context, email))

fun getUserNote(context: Context, email: String): String {
    return context.withDatabase {
        val noteKey = "$email.note"
        if (!it.exists(noteKey)) {
            it.put(noteKey, "")
        }
        it.get(noteKey)
    }!!
}

fun updateUserNote(context: Context, email: String, newNote: String) {
    context.withDatabase {
        it.put("$email.note", newNote)
    }
}
