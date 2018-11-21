package organize.fdjapplication.repository.interactor.playerInteractor

import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.repository.models.PlayersModel

interface PlayerInteractor {
    fun getPlayerList(teamName :String,teamListener: TeamListener)
}