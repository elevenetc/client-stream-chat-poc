package io.getstream.chat.android.core.poc.library.socket

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.Response
import java.io.IOException


class ErrorResponse : IOException() {

    @Expose
    @SerializedName("code")
    var code: Int = -1
    @Expose
    @SerializedName("message")
    override var message: String = ""
    @Expose
    @SerializedName("StatusCode")
    var statusCode: Int = -1
    @Expose
    @SerializedName("duration")
    var duration: String = ""

    companion object {

        const val TOKEN_EXPIRED_CODE = 40
        fun parseError(response: String?): ErrorResponse {
            return Gson().fromJson(response, ErrorResponse::class.java)
        }

        fun parseError(response: Response): ErrorResponse {
            val message: String
            return if (response.body() == null) {
                ErrorResponse()
            } else try { // avoid consuming the response body stream (might crash other readers)
                message = response.peekBody(Long.MAX_VALUE).string()
                parseError(message)
            } catch (e: Throwable) {
                ErrorResponse()
            }
        }
    }

}
