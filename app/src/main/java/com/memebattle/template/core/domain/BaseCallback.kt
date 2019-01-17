package com.memebattle.template.core.domain

interface BaseCallback<T> {
    fun onSuccess(data: T?)
    fun onError(t: Throwable)
}