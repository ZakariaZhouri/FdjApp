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
        val call = service.searchTeam(teamName)
        call.enqueue(object : Callback<PlayersModel> {
            override fun onResponse(call: Call<PlayersModel>, response: Response<PlayersModel>) {
                response.body()?.let { players ->
                    players.listPlayer?.let {
                        teamListener.onResponse(it)
                    } ?: teamListener.onFailure()
                }
            }

            override fun onFailure(call: Call<PlayersModel>, t: Throwable) {
                teamListener.onFailure()
            }
        })
    }

}