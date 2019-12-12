package id.muhadif.kade_sub_2.feature.matches

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import id.muhadif.kade_sub_2.R
import id.muhadif.kade_sub_2.databinding.FragmentMatchesBinding
import id.muhadif.kade_sub_2.feature.MainActivity
import id.muhadif.kade_sub_2.feature.matches.RecentNextFragment.Companion.ID_LEAGUE


class MatchesFragment : Fragment() {

    lateinit var binding : FragmentMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesBinding.bind(inflater.inflate(R.layout.fragment_matches, container, false))
        val activity = activity as MainActivity
        activity.setSupportActionBar(binding.toolbar)
        this.activity?.title = "League"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = MatchesFragmentAdapter(childFragmentManager, arguments!!.getString(ID_LEAGUE)!!)
        binding.vpMainMatches.adapter = adapter
        binding.tabsLayout.setupWithViewPager(binding.vpMainMatches)
    }

    companion object {
        fun newInstance(key : String) : MatchesFragment {
            val args = Bundle()
            args.putSerializable(ID_LEAGUE, key)
            return MatchesFragment().apply {
                arguments = args
            }
        }
    }


}
