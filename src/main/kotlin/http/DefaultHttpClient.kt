package http

import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class DefaultHttpClient(
  private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(CodeInterceptor()).build()
) : HttpClient {

  override fun get(urlGet: String): Response {
    val request: Request = Request.Builder()
      .url(urlGet)
      .addHeader("Authorization", Credentials.basic("moneyman", "1005"))
      .build()
    return client.newCall(request).execute()
  }

  override fun post(urlPost: String, bodyJSON: String): Response {
    val body: RequestBody = bodyJSON.toRequestBody("application/json".toMediaType())
    val request: Request = Request.Builder()
      .url(urlPost)
      .addHeader("Authorization", Credentials.basic("moneyman", "1005"))
      .post(body)
      .build()
    return client.newCall(request).execute()
  }

  override fun delete() {
    TODO("Not yet implemented")
  }
}