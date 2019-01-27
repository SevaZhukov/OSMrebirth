package com.memebattle.template.core.domain.util

interface   BaseCallback<T> {
    fun onSuccess(data: T?)
    fun onError(t: Throwable)
}