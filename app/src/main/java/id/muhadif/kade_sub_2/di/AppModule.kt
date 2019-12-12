package id.muhadif.kade_sub_2.di

import id.muhadif.kade_sub_2.data.remote.FootballApiClient
import id.muhadif.kade_sub_2.data.repository.FootballRepository
import id.muhadif.kade_sub_2.feature.dashboard.DashboardViewModel
import id.muhadif.kade_sub_2.feature.detailmatch.DetailMatchViewModel
import id.muhadif.kade_sub_2.feature.matches.MatchesViewModel
import id.muhadif.kade_sub_2.feature.matches.RecentNextFragment
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var appModule = module {
    single { FootballApiClient.getClient() }
    factory { FootballRepository(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { MatchesViewModel(get()) }
    viewModel { DetailMatchViewModel(get()) }
}