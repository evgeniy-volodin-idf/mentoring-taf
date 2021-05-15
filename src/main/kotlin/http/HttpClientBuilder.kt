package http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpClientBuilder {
  fun generateHttpClientWithInterceptors(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(CodeInterceptor())
      .addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
      )
      .build()
  }
}