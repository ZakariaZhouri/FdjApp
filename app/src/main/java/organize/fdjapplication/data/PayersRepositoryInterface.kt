package organize.fdjapplication.data

import io.reactivex.Observable
import organize.fdjapplication.repository.models.PlayersModel

interface PayersRepositoryInterface {
    fun searchTeam( teamName: String): Observable<PlayersModel>}