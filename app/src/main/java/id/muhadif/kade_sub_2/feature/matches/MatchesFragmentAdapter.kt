package id.muhadif.kade_sub_2.feature.matches

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.muhadif.kade_sub_2.data.remote.response.League
import id.muhadif.kade_sub_2.feature.dashboard.DashboardFragment
import id.muhadif.kade_sub_2.feature.matches.RecentNextFragment.Companion.LAST_MATCH
import id.muhadif.kade_sub_2.feature.matches.RecentNextFragment.Companion.NEXT_MATCH

class MatchesFragmentAdapter (fragmentManager: FragmentManager, val idLeague: String)
    : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) : Fragment {
        return when (position){
            0 -> RecentNextFragment.newInstance(LAST_MATCH, idLeague)
            1 -> RecentNextFragment.newInstance(NEXT_MATCH, idLeague)
            else -> RecentNextFragment.newInstance(LAST_MATCH, idLeague)
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Recent"
            1 -> "Upcoming"
            else -> ""
        }
    }


}