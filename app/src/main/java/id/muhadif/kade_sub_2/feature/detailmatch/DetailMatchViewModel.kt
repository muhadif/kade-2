package id.muhadif.kade_sub_2.feature.detailmatch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.muhadif.kade_sub_2.data.remote.ApiResponse
import id.muhadif.kade_sub_2.data.remote.response.Event
import id.muhadif.kade_sub_2.data.remote.response.Team
import id.muhadif.kade_sub_2.data.repository.FootballRepository
import id.muhadif.kade_sub_2.feature.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailMatchViewModel(val repository: FootballRepository) : BaseViewModel(){

    private val _match : MutableLiveData<Event> = MutableLiveData()
    val match : LiveData<Event>
        get() = _match
    val _homeTeam : MutableLiveData<Team> = MutableLiveData()
    val homeTeam : LiveData<Team>
        get() = _homeTeam
    val _awayTeam  : MutableLiveData<Team> = MutableLiveData()
    val awayTeam  : LiveData<Team>
        get() = _awayTeam

    fun fetchDetailMatch(idMatch : String) {
        coroutineContext.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = repository.getDetailMatch(idMatch)
                    when (response){
                        is ApiResponse.Success -> {
                            _match.postValue(response.body!!.events.first())
                        }
                        is ApiResponse.Error -> {
                            Log.e(this.javaClass.simpleName, "Cannot load detail match from id ${idMatch}")
                        }

                    }
                } catch (e : Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

    fun fetchDetailMatch(idTeam : String, isHome : Boolean) {
        coroutineContext.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = repository.getDetailTeam(idTeam)
                    when (response) {
                        is ApiResponse.Success -> {
                            if (isHome)
                                _homeTeam.postValue(response.body?.teams?.first())
                            else
                                _awayTeam.postValue(response.body?.teams?.first())
                        }
                        is ApiResponse.Error -> {
                            Log.e(this.javaClass.simpleName, "Cannot load detail team from id ${idTeam}")
                        }
                    }
                } catch (e : Exception) {
                    e.printStackTrace()
                }

            }
        }

    }
}