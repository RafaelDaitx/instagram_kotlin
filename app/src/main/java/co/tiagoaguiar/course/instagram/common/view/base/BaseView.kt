package co.tiagoaguiar.course.instagram.common.view.base

import co.tiagoaguiar.course.instagram.login.Login

interface BaseView<T> {
    var presenter: T
}