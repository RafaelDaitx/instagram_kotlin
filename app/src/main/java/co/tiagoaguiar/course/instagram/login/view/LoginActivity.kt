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
import co.tiagoaguiar.course.instagram.common.view.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.login.Login
import co.tiagoaguiar.course.instagram.login.presentation.LoginPresenter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        presenter = LoginPresenter(this)

        ///IGUAL A binding.loginEditEmail.addTextChangedListener(watcher), mas serve
        ///para reduzir linhas de código
        with(binding) {
            loginEditPassword.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            loginEditPassword.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })

            loginBtnEnter.setOnClickListener {
                presenter.login(loginEditEmail.text.toString(), loginEditEmail.text.toString())
                ///PASSANDO DADOS PARA O PRESENTER(INTERFACE) TRATAR LÁ
// TESTES -> Handler(Looper.getMainLooper()).postDelayed({
//          loginBtnEnter.showProgress(false)
//          ///Faz o delayed de dois segundos,e depois define para falso o que estád entro daqui
//        }, 2000)
            }
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString().isNotEmpty()
                && binding.loginEditPassword.text.toString().isNotEmpty()
        ///SE NÃO FOR VAZIO, ELE RETORNA VERDADEIRO E HABILITA O BOTÃO
    }


    ///INTERFACE QUE ADMINISTRA AS AÇÕES QUE OCORRERAM NO APP, A VIEW APENAS
    ///CUIDAR DA ACTIVITY (RENDERIZAR), TODA LOGICA FOI PARA A INTERFACE
    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.loginEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.loginEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        TODO("Not yet implemented")
    }

    override fun onUserUnathorized() {
        TODO("Not yet implemented")
    }

}