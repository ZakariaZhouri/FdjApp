package organize.fdjapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import organize.fdjapplication.R
import organize.fdjapplication.presenters.LiguePresenterImpl
import organize.fdjapplication.presenters.view.LigueView
import organize.fdjapplication.presenters.viewModel.TeamViewModel
import organize.fdjapplication.repository.interactor.ligueInteractor.TeamsRepository
import kotlinx.android.synthetic.main.activity_main.*
import organize.fdjapplication.presenters.listener.MainRecyclerListener
import organize.fdjapplication.ui.activity.PlayersActivity.Companion.TEAM_NAME
import organize.fdjapplication.ui.recycler.TeamsRecyclerAdapter


class TeamsActivity : AppCompatActivity(), LigueView, MainRecyclerListener {

    val presenter by lazy {
        LiguePresenterImpl(TeamsRepository(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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