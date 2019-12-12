package id.muhadif.kade_sub_2.data.remote

import id.muhadif.kade_sub_2.data.remote.response.LeaguesResponse
import id.muhadif.kade_sub_2.data.remote.response.MatchResponse
import id.muhadif.kade_sub_2.data.remote.response.TeamResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("json/1/all_leagues.php")
    fun getLeagues() : Deferred<Response<LeaguesResponse>>

    @GET("json/1/eventspastleague.php")
    fun getLastMatch(@Query("id") idLeague : String) : Deferred<Response<MatchResponse>>

    @GET("json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") idLeague : String) : Deferred<Response<MatchResponse>>

    @GET("json/1/lookupevent.php?")
    fun getDetailMatch(@Query("id") idMatch : String) : Deferred<Response<MatchResponse>>

    @GET("json/1/lookupteam.php?id=133604")
    fun getDetailTeam(@Query("id") idTeam : String) : Deferred<Response<TeamResponse>>

}