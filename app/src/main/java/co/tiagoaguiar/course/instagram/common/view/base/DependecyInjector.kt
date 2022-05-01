package co.tiagoaguiar.course.instagram.common.view.base

import co.tiagoaguiar.course.instagram.login.data.FakeDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository

object DependecyInjector {
    fun loginRepository() : LoginRepository {
        return LoginRepository(FakeDataSource())
    }
}