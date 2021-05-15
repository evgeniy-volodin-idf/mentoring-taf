package http

import core.config.AppConfig
import core.config.YamlConfig
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class DefaultHttpClient(
  private val client: OkHttpClient = HttpClientBuilder.generateHttpClientWithInterceptors(),
  private val config: AppConfig = YamlConfig().getConfig()
) : HttpClient {
  override fun get(urlGet: String): Response {
    val request: Request = Request.Builder()
      .url(urlGet)
      .addHeader(HEADER_AUTHORIZATION, Credentials.basic(config.authUser.user, config.authUser.pass))
      .build()
    return client.newCall(request).execute()
  }

  override fun post(urlPost: String, bodyJSON: String): Response {
    val body: RequestBody = bodyJSON.toRequestBody(MEDIA_TYPE_JSON)
    val request: Request = Request.Builder()
      .url(urlPost)
      .post(body)
      .build()
    return client.newCall(request).execute()
  }

  override fun delete() {
    TODO("Not yet implemented")
  }

  companion object {
    const val HEADER_AUTHORIZATION = "Authorization"
    val MEDIA_TYPE_JSON = "application/json".toMediaType()
  }
}