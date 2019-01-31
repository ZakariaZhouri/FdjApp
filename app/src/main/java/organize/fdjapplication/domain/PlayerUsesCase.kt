package organize.fdjapplication.domain

import android.os.SystemClock
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import organize.fdjapplication.data.PayersRepositoryInterface
import organize.fdjapplication.repository.models.PlayersModel


class PlayerUsesCase(val playerRepositoryInterface: PayersRepositoryInterface, val outPutPort: OutPutPort) {

    fun teamPlayer(teamName: String) {
        val observable = playerRepositoryInterface.searchTeam(teamName)
        observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(object : DisposableObserver<PlayersModel>() {
            override fun onComplete() {

            }

            override fun onNext(t: PlayersModel) {
                t.listPlayer?.get(0)?.playerName = "ZHOURI"


                outPutPort.getTeamPlayerPlayer(t)

            }

            override fun onError(e: Throwable) {
                Log.e("ERROE=====>", e.toString())

            }

        })
    }
}