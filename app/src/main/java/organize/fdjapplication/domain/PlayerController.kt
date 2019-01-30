package organize.fdjapplication.domain

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import organize.fdjapplication.data.PlayerRepository
import organize.fdjapplication.presenters.view.PlayerView
import organize.fdjapplication.presenters.viewModel.PlayerViewModel
import organize.fdjapplication.repository.models.PlayersModel
import java.util.*

class PlayerController(val view: PlayerView) {

    val playerRepository = PlayerRepository()
    val playerInteracto = PlayerUsesCase(playerRepository)
    fun getTeamPlayer(temaName: String) {
        playerInteracto.teamPlayer(temaName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableObserver<PlayersModel>() {
            override fun onComplete() {
            }

            override fun onNext(t: PlayersModel) {
                Log.e("Players===============>", t.toString())
            }

            override fun onError(e: Throwable) {
            }

        })

    }
}