package organize.fdjapplication.repository.interactor.ligueInteractor

import organize.fdjapplication.presenters.listener.TeamListener
import organize.fdjapplication.repository.api.FdjApi
import organize.fdjapplication.repository.models.LigueModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class TeamsRepository : LigueInteractor {
    companion object {
        const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
    }

    fun getRetrofitInstance(): Retrofit {
        return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    }

    override fun getLigueList(ligueName: String, teamListener: TeamListener) {
        val service: FdjApi = getRetrofitInstance().create()
        val call = service.searchLigue(ligueName)
        call.enqueue(object : Callback<LigueModel> {
            override fun onResponse(call: Call<LigueModel>, response: Response<LigueModel>) {
                response.body()?.let { teams ->
                    teams.ligueTeams?.let {
                        teamListener.onResponse(it)
                    } ?: teamListener.onFailure()
                }
            }

            override fun onFailure(call: Call<LigueModel>, t: Throwable) {
                teamListener.onFailure()
            }
        })

    }
}