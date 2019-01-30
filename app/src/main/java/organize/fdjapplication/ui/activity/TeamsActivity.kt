package organize.fdjapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import organize.fdjapplication.R
import organize.fdjapplication.presenters.LiguePresenterImpl
import organize.fdjapplication.presenters.view.LigueView
import organize.fdjapplication.presenters.viewModel.TeamViewModel
import organize.fdjapplication.repository.interactor.ligueInteractor.TeamsRepository
import kotlinx.android.synthetic.main.activity_teams.*
import organize.fdjapplication.presenters.listener.MainRecyclerListener
import organize.fdjapplication.ui.activity.PlayersActivity.Companion.TEAM_NAME
import organize.fdjapplication.ui.recycler.TeamsRecyclerAdapter
import android.os.SystemClock


class TeamsActivity : AppCompatActivity(), LigueView, MainRecyclerListener {

    val presenter by lazy {
        LiguePresenterImpl(TeamsRepository(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        val list = listOf("a", "b", "c")
        val serverDownloadObservable = Observable.create<Int> { emitter ->
            SystemClock.sleep(10000) // simulate delay
            emitter.onNext(5)
            emitter.onComplete()
        }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        val disposable = serverDownloadObservable.subscribe { result ->
            team_name_edt.setText(result.toString())
        }
        disposable.dispose()
        Observable.just(list)
                .subscribeOn(Schedulers.newThread())
                //each subscription is going to be on a new thread.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<String>> {


                    override fun onNext(t: List<String>) {
                        Log.e("Output", t.get(0))
                    }


                    override fun onComplete() {
                        Log.e("onComplete", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("onSubscribe", d.toString())

                    }

                    override fun onError(e: Throwable) {
                        Log.e("onError", "onError")
                    }
                })
    }

    private fun displayResult(result: String?) {
        result?.let { it ->
            team_name_edt.setText(it)
        }
    }

    private fun fetchFromServer(): String {
        return "String"
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        ok_button.setOnClickListener { view -> onClickButonOk() }
    }

    private fun onClickButonOk() {
        presenter.getLigueTeams(team_name_edt.text.toString())
    }

    override fun showProgress() {
        ok_button.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        ok_button.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    override fun showLigueTeam(listTeam: List<TeamViewModel>) {
        teams_recycler.visibility = View.VISIBLE
        ok_button.visibility = View.GONE
        teams_recycler.layoutManager = LinearLayoutManager(this)
        teams_recycler.adapter = TeamsRecyclerAdapter(listTeam, this)
    }

    override fun onItemClicked(teamName: String?) {
        teamName?.let {
            val intent = Intent(this, PlayersActivity::class.java)
            intent.putExtra(TEAM_NAME, it)
            startActivity(intent)
        }
    }

    override fun onFailure() {
        ok_button.text = "Reset"
    }


}
