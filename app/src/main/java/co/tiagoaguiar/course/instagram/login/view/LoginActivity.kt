package co.tiagoaguiar.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    ///IGUAL A binding.loginEditEmail.addTextChangedListener(watcher), mas serve
    ///para reduzir linhas de código
    with(binding){
      loginEditEmail.addTextChangedListener(watcher)
      binding.loginEditPassword.addTextChangedListener(watcher)

        loginBtnEnter.setOnClickListener{
        loginBtnEnter.showProgress(true)
        loginEditEmailInput.error = "Esse email é inválido"
        loginEditPasswordInput.error = "Senha incorreta"

        Handler(Looper.getMainLooper()).postDelayed({
          loginBtnEnter.showProgress(false)
          ///Faz o delayed de dois segundos,e depois define para falso o que estád entro daqui
        }, 2000)
      }
    }
  }

  private val watcher = object : TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      binding.loginBtnEnter.isEnabled = s.toString().isNotEmpty()
      ///SE NÃO FOR VAZIO, ELE RETORNA VERDADEIRO E HABILITA O BOTÃO
    }

    override fun afterTextChanged(s: Editable?) {

    }

  }
}