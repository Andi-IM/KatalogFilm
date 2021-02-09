@file:Suppress("unused")

package id.airham.moviecatalogue.data.source.remote

import id.airham.moviecatalogue.data.source.remote.StatusResponse.ERROR
import id.airham.moviecatalogue.data.source.remote.StatusResponse.SUCCESS
import id.airham.moviecatalogue.data.source.remote.StatusResponse.EMPTY

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}