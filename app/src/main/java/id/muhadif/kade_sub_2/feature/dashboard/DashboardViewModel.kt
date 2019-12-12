package id.muhadif.kade_sub_2.feature.dashboard

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.data.model.LeagueModel
import id.muhadif.kade_sub_2.data.remote.response.League
import id.muhadif.kade_sub_2.feature.base.BaseViewModel
import kotlinx.coroutines.launch

class DashboardViewModel(val app: Application) : BaseViewModel(){

    private val _leagues = MutableLiveData<List<LeagueModel>>()
    val leagues : LiveData<List<LeagueModel>>
        get() = _leagues

    init {
        fetchLeagues()
    }

    private fun fetchLeagues(){

        coroutineContext.launch {
            val nameLeague = app.resources.getStringArray(R.array.name_league)
            val idLeague = app.resources.getStringArray(R.array.id_league)
            val descLeague = app.resources.getStringArray(R.array.desc_league)
            val logoLeague = app.resources.getStringArray(R.array.logo)

            val tempLeagues = mutableListOf<LeagueModel>()
            nameLeague.forEachIndexed{index, value ->
                tempLeagues.add(
                    LeagueModel(
                        idLeague[index].toString(),
                        value.toString(),
                        descLeague[index].toString(),
                        app.resources.getIdentifier("${logoLeague[index]}", "drawable", "id.muhadif.kade_sub_2")
                    )
                )
            }
            _leagues.postValue(tempLeagues)


        }




    }
}