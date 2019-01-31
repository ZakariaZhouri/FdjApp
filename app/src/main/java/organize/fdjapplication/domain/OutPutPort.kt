package organize.fdjapplication.domain

import io.reactivex.Observable
import organize.fdjapplication.repository.models.PlayersModel

interface OutPutPort {

    fun getTeamPlayerPlayer( playerModelObserver : PlayersModel)
}