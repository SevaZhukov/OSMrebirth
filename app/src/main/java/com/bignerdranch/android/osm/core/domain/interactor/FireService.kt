package com.bignerdranch.android.osm.core.domain.interactor

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseStorage
import com.memebattle.goldextensions.log
import java.io.File


class FireService(private val context: Context) {
    private var auth = FirebaseAuth.getInstance()
    private var storage = FirebaseStorage.getInstance().getReference()

    fun signIn(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
                .map { authResult -> authResult.user != null }
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun signUp(email: String, password: String, callBack: AuthCallBack) {
        RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
                .map { authResult -> authResult.user != null }
                .take(1)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun uploadData(callBack: UploadStorageCallBack) {
        val currentDBPath = context.getDatabasePath("database").absolutePath
        log("path $currentDBPath")
        val email = auth.currentUser!!.email
        storage.child("$email/database.db")
                .putFile(currentDBPath.toUri())
                .addOnSuccessListener {callBack.onSuccess(it)}
                .addOnFailureListener {callBack.onError(it)}
    }

    fun downloadData(callBack: DownloadStorageCallBack) {
        val email = auth.currentUser!!.email
        val localFile = File.createTempFile("database", "db")
        RxFirebaseStorage.getFile(storage.child(email!!), localFile)
                .subscribe({ callBack.onSuccess(it) }, { callBack.onError(it) })
    }

    fun isEnter(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }

    fun checkOnCloud() {
        val email = auth.currentUser!!.email
        Log.i("code",  "on cloud email $email")
    }

    interface AuthCallBack {
        fun onSuccess(success: Boolean)

        fun onError(e: Throwable)
    }

    interface DownloadStorageCallBack {
        fun onSuccess(taskSnapshot: FileDownloadTask.TaskSnapshot)

        fun onError(e: Throwable)
    }

    interface UploadStorageCallBack {
        fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot)

        fun onError(e: Throwable)
    }

    interface CloudCallBack {
        fun onSuccess()

        fun onError(e: Throwable)
    }
}