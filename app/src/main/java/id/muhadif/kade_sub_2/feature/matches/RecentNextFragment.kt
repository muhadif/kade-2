package id.muhadif.kade_sub_2.feature.matches


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.data.remote.response.Event
import id.muhadif.kade_sub_2.databinding.FragmentMatchesBinding
import id.muhadif.kade_sub_2.databinding.FragmentRecentNextBinding
import id.muhadif.kade_sub_2.feature.detailmatch.DetailMatchActivity
import id.muhadif.kade_sub_2.feature.detailmatch.DetailMatchActivity.Companion.MATCH_ID
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class RecentNextFragment : Fragment() {

    lateinit var binding : FragmentRecentNextBinding
    private val viewModel : MatchesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecentNextBinding.bind(inflater.inflate(R.layout.fragment_recent_next, container, false))
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        loadMatches()

        viewModel.matches.observe(this@RecentNextFragment, Observer {
            if (it == null) return@Observer

            initRecyclerView(it.toMatchItem())
        })

    }



    private fun loadMatches() {
        val idLeague = arguments?.getString(ID_LEAGUE)!!
        when (arguments?.getString(TIME)) {
            LAST_MATCH -> {
                viewModel.fetchLastMatches(idLeague)
            }
            NEXT_MATCH -> {
                viewModel.fetchNextMatch(idLeague)
            }
        }

    }

    private fun initRecyclerView(matchItems : List<MatchItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(matchItems)
        }

        binding.rvMatches.apply{
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, _ ->
            (item as MatchItem).let {
                val detailMatchIntent = Intent(context, DetailMatchActivity::class.java)
                detailMatchIntent.putExtra(MATCH_ID, item.match.idEvent)
                startActivity(detailMatchIntent)
            }
        }
    }

    private fun List<Event>.toMatchItem() : List<MatchItem> {
        return this.map {
            MatchItem(it)
        }
    }

    companion object {
        const val LAST_MATCH: String = "last_match"
        const val NEXT_MATCH: String = "next_match"
        const val ID_LEAGUE : String = "id_league"
        private const val TIME = "time"
        fun newInstance(time : String, idLeague : String) : RecentNextFragment {
            val args = Bundle()
            args.putSerializable(TIME, time)
            args.putSerializable(ID_LEAGUE, idLeague)
            return RecentNextFragment().apply {
                arguments = args
            }
        }
    }




}
