package organize.fdjapplication.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_players.*
import organize.fdjapplication.R
import organize.fdjapplication.presenters.PlayerPresenterImpl
import organize.fdjapplication.presenters.view.PlayerView
import organize.fdjapplication.presenters.viewModel.PlayerViewModel
import organize.fdjapplication.repository.interactor.playerInteractor.PlayerRepository
import organize.fdjapplication.ui.recycler.PlayerRecyclerAdapter

class PlayersActivity : AppCompatActivity(), PlayerView {

    val presenter by lazy {
        PlayerPresenterImpl(PlayerRepository(), this)
    }

    companion object {
        const val TEAM_NAME = "teamName"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

    }

    override fun onResume() {
        super.onResume()
        val teamName = intent.extras.getString(TEAM_NAME)
        presenter.getPlayerList(teamName)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun onFailure() {

    }

    override fun showPlayerTeam(listPlayer: List<PlayerViewModel>) {
        players_recycler.adapter = PlayerRecyclerAdapter(listPlayer)
        players_recycler.layoutManager = LinearLayoutManager(this)

    }

}