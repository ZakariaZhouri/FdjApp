package organize.fdjapplication.domain

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import organize.fdjapplication.data.PayersRepositoryInterface
import organize.fdjapplication.repository.models.PlayersModel


class PlayerUsesCase(val playerRepositoryInterface: PayersRepositoryInterface) {

    fun teamPlayer(teamName: String): Observable<PlayersModel> {
        val observable = playerRepositoryInterface.searchTeam(teamName)
        val disposable = observable.subscribe(object : Observer<PlayersModel> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(playerModel: PlayersModel) {
                playerModel.listPlayer?.get(0)?.playerName = "ZAKARIA"
            }

            override fun onError(e: Throwable) {
            }

        })


        //      subscribe { playerModel ->
        //
        //}
        return observable
    }
}