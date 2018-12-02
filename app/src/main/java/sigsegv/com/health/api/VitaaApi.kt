package sigsegv.com.health.api

import com.google.gson.Gson
import okhttp3.*
import sigsegv.com.health.api.entities.*

private const val BASE_URL = "https://devportal.viita-watches.com/api/v1"
private val JSON_MEDIA = MediaType.parse("application/json")
private val gson = Gson()
private val http = OkHttpClient()
private val defaultDeviceCommand = ViitaRegisterDeviceCommand(
    null,
    "Android",
    "10.3.3",
    "1.0.0",
    "Note 4",
    null,
    "tr-tr"
)

fun registerUserApi(email: String, password: String): ViitaSignInUserResponse {
    val credentials = ViitaCredentials(email, password)
    val signInCommand = ViitaCompositeSignInCommand(credentials, defaultDeviceCommand)
    val request = Request.Builder()
        .url("$BASE_URL/auth/signin")
        .post(RequestBody.create(JSON_MEDIA, gson.toJson(signInCommand)))
        .build()
    val response = http.newCall(request).execute()
    val signInData = gson.fromJson(response.body()!!.string(), ViitaSignInUserResponse::class.java).copy(token = response.header("X-Auth-Token")!!)
    return signInData
}

fun fetchUserData(token: String): ViitaFullDataResponse {
    val request = Request.Builder()
        .url("$BASE_URL/data")
        .header("X-Auth-Token", token)
        .build()
    val response = http.newCall(request).execute()

    return gson.fromJson(response.body()!!.string(), ViitaFullDataResponse::class.java)
}
