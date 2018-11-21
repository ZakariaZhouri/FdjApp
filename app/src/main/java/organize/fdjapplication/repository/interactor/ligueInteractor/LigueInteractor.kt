package organize.fdjapplication.repository.interactor.ligueInteractor

import organize.fdjapplication.presenters.listener.TeamListener

interface LigueInteractor {
    fun getLigueList(ligueName : String,teamListener: TeamListener)
}