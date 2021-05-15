package http

import okhttp3.Response

interface HttpClient {
  fun get(urlGet: String): Response

  fun post(urlPost: String, bodyJSON: String): Response

  fun delete()
}