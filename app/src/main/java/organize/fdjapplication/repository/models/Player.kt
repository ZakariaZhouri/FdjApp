package organize.fdjapplication.repository.models

import com.google.gson.annotations.SerializedName

data class Player(@SerializedName("strPlayer") var playerName: String,
                  @SerializedName("strPosition") val playerPosition: String,
                  @SerializedName("strNationality") val playerNationality: String,
                  @SerializedName("strSigning") val playerPrice: String,
                  @SerializedName("dateBorn") val playerDateBorn: String,
                  @SerializedName("strCutout") val playerImage: String)