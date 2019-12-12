package id.muhadif.kade_sub_2.feature.detailmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.api.load
import com.google.android.material.appbar.AppBarLayout
import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.data.remote.response.Event
import id.muhadif.kade_sub_2.databinding.ActivityDetailMatchBinding
import org.koin.android.ext.android.inject

class DetailMatchActivity : AppCompatActivity() {

    lateinit var matchId : String
    lateinit var match : Event
    private val viewModel : DetailMatchViewModel by inject()
    lateinit var binding : ActivityDetailMatchBinding
    lateinit var matchTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_match)
        binding.detailViewModel = viewModel
        setSupportActionBar(binding.toolbar)
        setAllTitle("")

        binding.appbarDetail.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener{
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(p0: AppBarLayout?, p1: Int) {
                if (scrollRange == -1) {
                    scrollRange = binding.appbarDetail.totalScrollRange
                }
                if (scrollRange + p1 == 0) {
                    setAllTitle(matchTitle)
                    isShow = true
                } else if (isShow) {
                    setAllTitle("")
                    isShow = false
                }
            }

        })


        matchId = intent.getStringExtra(MATCH_ID)!!

        viewModel.fetchDetailMatch(matchId)

        viewModel.match.observe(this@DetailMatchActivity, Observer {
            this.match = it
            matchTitle = "${it.strHomeTeam} vs ${it.strAwayTeam}"
            viewModel.fetchDetailMatch(match.idHomeTeam, true)
            viewModel.fetchDetailMatch(match.idAwayTeam, false)
            showMatch()
        })


    }

    private fun setAllTitle(title: String) {
        binding.collapDetail.title = title
        this.title = title
    }

    private fun showMatch() {
        match.let {
            binding.match = it
            if(it.intHomeScore != "null" && it.intAwayScore == "null")
                binding.tvScore.text = "${it.intHomeScore} - $${it.intAwayScore}"
        }

        viewModel.homeTeam.observe(this@DetailMatchActivity, Observer {
            binding.ivHomeLogo.load(it.strTeamBadge)
        })

        viewModel.awayTeam.observe(this@DetailMatchActivity, Observer {
            binding.ivAwayLogo.load(it.strTeamBadge)
        })
    }

    companion object {
        const val MATCH_ID = "match_id"
    }
}
