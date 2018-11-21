package organize.fdjapplication.repository.api

import organize.fdjapplication.repository.interactor.ligueInteractor.TeamsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    fun getRetrofitInstance(): Retrofit {
        return retrofit2.Retrofit.Builder()
                .baseUrl(TeamsRepository.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    }
}