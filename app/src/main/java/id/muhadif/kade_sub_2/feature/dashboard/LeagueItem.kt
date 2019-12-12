package id.muhadif.kade_sub_2.feature.dashboard

import coil.api.load
import com.xwray.groupie.databinding.BindableItem
import id.muhadif.kade_sub_2.databinding.ItemLeagueBinding
import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.data.model.LeagueModel


class LeagueItem(val league: LeagueModel) : BindableItem<ItemLeagueBinding>() {
    override fun getLayout() = R.layout.item_league

    override fun bind(viewBinding: ItemLeagueBinding, position: Int) {
        viewBinding.league = league
        viewBinding.ivLeague.load(league.logo)
    }

}