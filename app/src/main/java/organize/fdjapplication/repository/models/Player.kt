package organize.fdjapplication.repository.models

import com.google.gson.annotations.SerializedName

data class Player( @SerializedName("strNationality") val playerNationality: String,
                   @SerializedName("strPlayer") val playerName: String,
                   @SerializedName("dateBorn") val playerDateBorn: String )