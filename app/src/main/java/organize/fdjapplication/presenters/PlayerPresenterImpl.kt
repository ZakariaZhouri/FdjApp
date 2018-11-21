package organize.fdjapplication.presenters

import organize.fdjapplication.presenters.listener.PlayerPrensenter
import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.presenters.view.PlayerView
import organize.fdjapplication.presenters.viewModel.PlayerViewModel
import organize.fdjapplication.repository.interactor.playerInteractor.PlayerInteractor

class PlayerPresenterImpl(val repository: PlayerInteractor, val view: PlayerView) : PlayerPrensenter, TeamListener {
    override fun getPlayerList(teamName: String) {
        repository.getPlayerList(teamName, this)
    }

    override fun onResponse(list: List<Any>) {
        view.hideProgress()
        view.showPlayerTeam(list as List<PlayerViewModel>)
    }

    override fun onFailure() {
        view.hideProgress()
    }
}