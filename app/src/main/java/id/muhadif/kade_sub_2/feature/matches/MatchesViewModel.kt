package id.muhadif.kade_sub_2.feature.matches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.muhadif.kade_sub_2.data.remote.ApiResponse
import id.muhadif.kade_sub_2.data.remote.response.Event
import id.muhadif.kade_sub_2.data.remote.response.League
import id.muhadif.kade_sub_2.data.repository.FootballRepository
import id.muhadif.kade_sub_2.feature.base.BaseViewModel
import id.muhadif.kade_sub_2.feature.base.ViewCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MatchesViewModel(private val repository: FootballRepository) : BaseViewModel(){

    private val _matches : MutableLiveData<List<Event>> = MutableLiveData()
    val matches : LiveData<List<Event>>
        get() = _matches
    private val _match : MutableLiveData<Event> = MutableLiveData()
    val match : LiveData<Event>
        get() = _match


    fun fetchLastMatches(idLeague: String) {

        coroutineContext.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = repository.getLastMatches(idLeague)
                    when (response) {
                        is ApiResponse.Success -> {
                            _matches.postValue(response.body?.events)
                        }
                        is ApiResponse.Error -> {
                            Log.e(this.javaClass.simpleName, "cannot load list matchs from id league = $idLeague")
                        }
                    }
                } catch (e : Exception) {
                    e.printStackTrace()

                }

            }
        }


    }

    fun fetchNextMatch(idLeague: String) {

        coroutineContext.launch {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = repository.getNextMatches(idLeague)
                    when (response) {
                        is ApiResponse.Success -> {
                            _matches.postValue(response.body?.events)
                        }
                        is ApiResponse.Error -> {
                            Log.e(this.javaClass.simpleName, "cannot load list matchs from id league = $idLeague")
                        }
                    }
                } catch (e : Exception) {
                    e.printStackTrace()
                }

            }
        }

    }

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
}