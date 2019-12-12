package id.muhadif.kade_sub_2.data.remote

import retrofit2.http.Body

sealed class ApiResponse<T> {
    data class Success<T>(val body : T) : ApiResponse<T>()
    data class Error<T>(val message : String, val errorCode : Int) : ApiResponse<T>()
}