package organize.fdjapplication.repository.interactor.playerInteractor

import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.repository.api.FdjApi
import organize.fdjapplication.repository.api.RetrofitInstance
import organize.fdjapplication.repository.models.PlayersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PlayerRepository : PlayerInteractor {


    override fun getPlayerList(teamName: String, teamListener: TeamListener) {
        RetrofitInstance.getRetrofitInstance()

        val service = RetrofitInstance.getRetrofitInstance().create<FdjApi>()
     //   val call = service.searchTeam(teamName)

    }

}