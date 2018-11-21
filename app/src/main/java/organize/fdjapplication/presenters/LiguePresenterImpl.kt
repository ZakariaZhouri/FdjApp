package organize.fdjapplication.presenters

import organize.fdjapplication.presenters.listener.LiguePresenter
import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.presenters.view.LigueView
import organize.fdjapplication.presenters.viewModel.TeamViewModel
import organize.fdjapplication.repository.interactor.ligueInteractor.LigueInteractor
import organize.fdjapplication.repository.models.TeamInfoModel

class LiguePresenterImpl(val repository: LigueInteractor, val view: LigueView) : LiguePresenter, TeamListener {
    override fun onResponse(list: List<Any>) {
        view.hideProgress()
        view.showLigueTeam(transformResponse(list as List<TeamInfoModel>))
    }

    override fun onFailure() {
        view.hideProgress()
        view.onFailure()

    }

    override fun getLigueTeams(ligueName: String) {
        view.showProgress()
        repository.getLigueList(ligueName, this)
    }

    private fun transformResponse(list: List<TeamInfoModel>): List<TeamViewModel> {
        val listViewModel = ArrayList<TeamViewModel>()
        list.forEach { item ->
            listViewModel.add(TeamViewModel(item.teamName))
        }
        return listViewModel
    }

}