package id.muhadif.kade_sub_2.data.repository

import id.muhadif.kade_sub_2.data.remote.response.LeaguesResponse
import id.muhadif.kade_sub_2.data.remote.response.MatchResponse
import id.muhadif.kade_sub_2.data.remote.response.TeamResponse
import id.muhadif.kade_sub_2.data.remote.ApiResponse
import id.muhadif.kade_sub_2.data.remote.FootballApiService


class FootballRepository(private val footballApiService: FootballApiService) {

    suspend fun getLeagues() : ApiResponse<LeaguesResponse?> {
        val apiResponse = footballApiService.getLeagues().await()
        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body())
        else
            ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }

    suspend fun getLastMatches(idLeague : String): ApiResponse<MatchResponse?> {
        val apiResponse = footballApiService.getLastMatch(idLeague).await()
        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body())
        else
            ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }

    suspend fun getNextMatches(idLeague : String) : ApiResponse<MatchResponse?> {
        val apiResponse = footballApiService.getNextMatch(idLeague).await()
        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body())
        else
            ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }

    suspend fun getDetailMatch(idMatch : String) : ApiResponse<MatchResponse?> {
        val apiResponse = footballApiService.getDetailMatch(idMatch).await()
        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body())
        else
            ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }

    suspend fun getDetailTeam(idTeam : String) : ApiResponse<TeamResponse?>{
        val apiResponse = footballApiService.getDetailTeam(idTeam).await()
        return if (apiResponse.isSuccessful)
            ApiResponse.Success(apiResponse.body())
        else
            ApiResponse.Error(apiResponse.message(), apiResponse.code())
    }
}