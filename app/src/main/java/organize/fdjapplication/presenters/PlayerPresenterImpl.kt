package organize.fdjapplication.presenters

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import organize.fdjapplication.data.PlayerRepository
import organize.fdjapplication.domain.OutPutPort
import organize.fdjapplication.domain.PlayerController
import organize.fdjapplication.domain.PlayerUsesCase
import organize.fdjapplication.presenters.listener.PlayerPrensenter
import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.presenters.view.PlayerView
import organize.fdjapplication.presenters.viewModel.PlayerViewModel
import organize.fdjapplication.repository.interactor.playerInteractor.PlayerInteractor
import organize.fdjapplication.repository.models.Player
import organize.fdjapplication.repository.models.PlayersModel

class PlayerPresenterImpl(val repository: PlayerInteractor, val view: PlayerView) : PlayerPrensenter, TeamListener, OutPutPort {
    override fun getTeamPlayerPlayer(playerModelObserver: PlayersModel) {
        Observable.create<PlayersModel> { emitter ->

            emitter.onNext(playerModelObserver)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableObserver<PlayersModel>() {
            override fun onComplete() {
            }

            override fun onNext(t: PlayersModel) {
                Log.e("TEST=====>", t.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("ERROE=====>", e.toString())

            }

        })
    }

    override fun getPlayerList(teamName: String) {
        val repositpry = PlayerRepository()
        val playerUsesCase = PlayerUsesCase(repositpry, this)
        val playerConroler = PlayerController(view)
        //   playerConroler.getTeamPlayer(teamName)
        playerUsesCase.teamPlayer(teamName)
        // repository.getPlayerList(teamName, this)
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