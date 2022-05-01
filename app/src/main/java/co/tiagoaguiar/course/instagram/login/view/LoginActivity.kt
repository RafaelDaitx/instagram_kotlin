package co.tiagoaguiar.course.instagram.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.tiagoaguiar.course.instagram.common.view.base.DependecyInjector
import co.tiagoaguiar.course.instagram.common.view.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import co.tiagoaguiar.course.instagram.login.Login
import co.tiagoaguiar.course.instagram.login.data.FakeDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository
import co.tiagoaguiar.course.instagram.login.presentation.LoginPresenter
import co.tiagoaguiar.course.instagram.main.view.MainActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ///Injetando depencência (injeta um objeto para outro obnjeto que depende dele)
        presenter = LoginPresenter(this, DependecyInjector.loginRepository())

        ///IGUAL A binding.loginEditEmail.addTextChangedListener(watcher), mas serve
        ///para reduzir linhas de código
        with(binding) {
            loginEditEmail.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(watcher)

            loginEditEmail.addTextChangedListener(TxtWatcher{
                displayEmailFailure(null)
            })
            loginEditPassword.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })

            loginBtnEnter.setOnClickListener {
                presenter.login(loginEditEmail.text.toString(), loginEditPassword.text.toString())
                ///PASSANDO DADOS PARA O PRESENTER(INTERFACE) TRATAR LÁ

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
       val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnathorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}