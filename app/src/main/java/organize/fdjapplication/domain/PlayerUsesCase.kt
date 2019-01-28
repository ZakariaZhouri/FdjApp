package organize.fdjapplication.domain

import android.database.Observable
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import organize.fdjapplication.data.PayersRepositoryInterface
import organize.fdjapplication.repository.models.PlayersModel

class PlayerUsesCase(val playerRepositoryInterface: PayersRepositoryInterface) {

    fun teamPlayer(teamName: String) {
        val obsarvable = playerRepositoryInterface.searchTeam(teamName)
        obsarvable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PlayersModel> {
                    override fun onComplete() {
                        Log.e("onComplete", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("onSubscribe", "onSubscribe")
                    }

                    override fun onNext(t: PlayersModel) {
                        Log.e("onNext", t.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("onError", "onError")

                    }

                })
    }
}