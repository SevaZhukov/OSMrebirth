package com.bignerdranch.android.osm.core.domain.util

interface   BaseCallback<T> {
    fun onSuccess(data: T?)
    fun onError(t: Throwable)
}