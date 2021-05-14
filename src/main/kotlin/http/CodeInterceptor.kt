package http

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CodeInterceptor : Interceptor{
  override fun intercept(chain: Interceptor.Chain): Response {
    val request: Request = chain.request()
    val response: Response = chain.proceed(request)

    if (!response.isSuccessful) throw Exception(
      "API call is not successful. Code: ${response.code} Message: ${response.message}")
    return response
  }
}