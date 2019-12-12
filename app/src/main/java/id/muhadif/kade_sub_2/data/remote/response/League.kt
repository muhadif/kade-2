package id.muhadif.kade_sub_2.data.remote.response

data class League(
    val idLeague: String,
    val strLeague: String,
    val strLeagueAlternate: String,
    val strSport: String
) {
    override fun toString(): String {
        return strLeague
    }
}