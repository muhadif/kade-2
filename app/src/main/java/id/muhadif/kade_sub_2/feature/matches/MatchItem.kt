package id.muhadif.kade_sub_2.feature.matches

import android.util.Log
import id.muhadif.kade_sub_2.R
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import id.muhadif.kade_sub_2.data.remote.response.Event
import id.muhadif.kade_sub_2.databinding.ItemMatchBinding
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.verbose


class MatchItem(val match : Event) : BindableItem<ItemMatchBinding>() {

    override fun getLayout(): Int = R.layout.item_match

    override fun bind(viewBinding: ItemMatchBinding, position: Int) {
        viewBinding.matchTeam = match
    }



}