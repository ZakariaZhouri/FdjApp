package organize.fdjapplication.repository.models

import com.google.gson.annotations.SerializedName

data class PlayersModel(@SerializedName("player") val listPlayer: List<Player>?) {
}