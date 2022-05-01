package co.tiagoaguiar.course.instagram.login.data

import android.os.Looper
import android.os.Handler
///SIIMULAÇÃO DE CAHAMADA DO SERVIDOR
class FakeDataSource : LoginDataSource{
    override fun login(email: String, password: String, callback: LoginCallback) {
      Handler(Looper.getMainLooper()).postDelayed({
          if(email == "a@a.com" && password == "12345678"){
              callback.onSucess()
          } else {
              println(email)
              println(password)
              callback.onFailure("Usário não encontrado")
              callback.onComplete()
          }
        }, 2000)
    }
}