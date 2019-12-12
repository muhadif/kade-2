package id.muhadif.kade_sub_2.feature.dashboard

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import id.muhadif.kade_sub_2.databinding.FragmentDashboardBinding

import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.data.model.LeagueModel
import id.muhadif.kade_sub_2.feature.MainActivity
import org.koin.android.ext.android.inject
import id.muhadif.kade_sub_2.feature.matches.MatchesFragment

class DashboardFragment : Fragment() {

    private val viewModel : DashboardViewModel by inject()
    lateinit var binding : FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.bind(inflater.inflate(R.layout.fragment_dashboard, container, false))
        val activity = activity as MainActivity
        this.activity?.title = "Dashboard"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.leagues.observe(this@DashboardFragment, Observer {
            initRecyclerView(it.toLeagueItem())
        })

    }

    private fun initRecyclerView(leagueItems :List<LeagueItem>){
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(leagueItems)
        }

        binding.rvLeagues.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, _ ->
            (item as LeagueItem).let {
                fragmentManager!!.beginTransaction()
                    .replace(R.id.fl_main, MatchesFragment.newInstance(it.league.idLeague))
                    .commit()
            }

        }

    }

    private fun List<LeagueModel>.toLeagueItem() : List<LeagueItem> {
        return this.map {
            LeagueItem(it)
        }
    }

}
