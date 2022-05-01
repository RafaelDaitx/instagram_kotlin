package co.tiagoaguiar.course.instagram.login.data

interface LoginCallback {
    fun onSucess()
    fun onFailure(message: String)
    fun onComplete()
}