package organize.fdjapplication.presenters.listener

interface TeamListener {

    fun onResponse(list :List<Any>)
    fun onFailure()
}