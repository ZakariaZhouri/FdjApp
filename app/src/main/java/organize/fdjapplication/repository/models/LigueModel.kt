package organize.fdjapplication.repository.models

import com.google.gson.annotations.SerializedName

data class LigueModel( @SerializedName("teams") val ligueTeams: List<TeamInfoModel>?) {
}