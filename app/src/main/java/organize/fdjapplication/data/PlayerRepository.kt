package organize.fdjapplication.data

import io.reactivex.Observable
import organize.fdjapplication.repository.api.FdjApi
import organize.fdjapplication.repository.interactor.ligueInteractor.TeamsRepository
import organize.fdjapplication.repository.models.PlayersModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class PlayerRepository : PayersRepositoryInterface {
    override fun searchTeam(teamName: String): Observable<PlayersModel> {
        val retrofit = Retrofit.Builder()
                .baseUrl(TeamsRepository.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create<FdjApi>()
        return service.searchTeam(teamName)
    }

}