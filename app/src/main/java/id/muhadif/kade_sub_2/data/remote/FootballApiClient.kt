package id.muhadif.kade_sub_2.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballApiClient {
    fun getClient() : FootballApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApiService::class.java)
    }
}