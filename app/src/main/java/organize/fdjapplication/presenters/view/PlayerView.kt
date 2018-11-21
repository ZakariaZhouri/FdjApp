package organize.fdjapplication.presenters.view

import organize.fdjapplication.presenters.viewModel.PlayerViewModel

interface PlayerView : MainView {
    fun showPlayerTeam(listPlayer : List<PlayerViewModel>)

}