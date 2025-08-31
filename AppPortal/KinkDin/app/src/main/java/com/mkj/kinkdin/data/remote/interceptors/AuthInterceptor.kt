package com.mkj.kinkdin.data.remote.interceptors

import com.mkj.kinkdin.util.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = preferencesManager.getAuthToken()

        return if (token != null && !isAuthEndpoint(request.url.encodedPath)) {
            val authenticatedRequest = request.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(authenticatedRequest)
        } else {
            chain.proceed(request)
        }
    }

    private fun isAuthEndpoint(path: String): Boolean {
        return path.contains("/login") ||
                path.contains("/signup") ||
                path.contains("/refresh") ||
                path.contains("/auth/")
    }
}
