package organize.fdjapplication.presenters

import organize.fdjapplication.data.PlayerRepository
import organize.fdjapplication.domain.PlayerController
import organize.fdjapplication.domain.PlayerUsesCase
import organize.fdjapplication.presenters.listener.PlayerPrensenter
import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.presenters.view.PlayerView
import organize.fdjapplication.presenters.viewModel.PlayerViewModel
import organize.fdjapplication.repository.interactor.playerInteractor.PlayerInteractor
import organize.fdjapplication.repository.models.Player

class PlayerPresenterImpl(val repository: PlayerInteractor, val view: PlayerView) : PlayerPrensenter, TeamListener {
    override fun getPlayerList(teamName: String) {
        val repositpry = PlayerRepository()
        val playerUsesCase = PlayerUsesCase(repositpry)
        val playerConroler = PlayerController(view)
        playerConroler.getTeamPlayer(teamName)
        playerUsesCase.teamPlayer(teamName)
        repository.getPlayerList(teamName, this)
    }

    override fun onResponse(list: List<Any>) {
        view.hideProgress()
        view.showPlayerTeam(transform(list as List<Player>))
    }

    override fun onFailure() {
        view.hideProgress()
    }

    private fun transform(list: List<Player>): List<PlayerViewModel> {
        val listPlayerViewModels = ArrayList<PlayerViewModel>()
        list.forEach { player ->
            listPlayerViewModels.add(PlayerViewModel(player.playerName,
                    player.playerPosition,
                    player.playerDateBorn,
                    player.playerNationality,
                    player.playerPrice,
                    player.playerImage
            ))
        }
        return listPlayerViewModels
    }
}