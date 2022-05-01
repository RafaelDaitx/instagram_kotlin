package co.tiagoaguiar.course.instagram.login

import co.tiagoaguiar.course.instagram.common.view.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.view.base.BaseView

interface Login {

    ///Camada presenter
    interface Presenter : BasePresenter{
        fun login(email: String, password: String)

    }


    //camada view
    interface View: BaseView<Presenter> {
        fun showProgress(enabledd : Boolean)
        fun displayEmailFailure(emailError: Int?)
        fun displayPasswordFailure(passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnathorized(message: String)
    }

}