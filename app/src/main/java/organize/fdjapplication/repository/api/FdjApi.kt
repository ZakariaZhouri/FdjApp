package organize.fdjapplication.repository.api

import io.reactivex.Observable
import organize.fdjapplication.repository.models.LigueModel
import organize.fdjapplication.repository.models.PlayersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FdjApi {

    @GET("search_all_teams.php")
    fun searchLigue(@Query("l") ligueName: String): Call<LigueModel>

    @GET("searchplayers.php")
    fun searchTeam(@Query("t") teamName: String): Observable<PlayersModel>
}