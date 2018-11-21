package organize.fdjapplication.presenters.view

import organize.fdjapplication.presenters.view.MainView
import organize.fdjapplication.presenters.viewModel.TeamViewModel

interface LigueView : MainView {

    fun showLigueTeam(listTeam : List<TeamViewModel>)
}