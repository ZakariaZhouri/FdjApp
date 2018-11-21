package organize.fdjapplication.presenters.view

interface MainView {

    fun onPause()
    fun onResume()
    fun showProgress()
    fun hideProgress()
    fun onFailure()
}