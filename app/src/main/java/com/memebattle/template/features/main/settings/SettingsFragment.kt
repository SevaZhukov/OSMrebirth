package com.memebattle.template.features.main.settings

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.template.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsVersion.setOnClickListener {
            onVersionClick()
        }
        setMark_in_play.setOnClickListener {
            onSetMarkClick()
        }
        settingsAuth.setOnClickListener {
            onAuthClick()
        }
        deleteAll.setOnClickListener {
            onDeleteAllClick()
        }
    }

    private fun onDeleteAllClick() {
        AlertDialog.Builder(activity)
                .setTitle("Удаление всех записей")
                .setMessage("Вы действительно удалить все записи?")
                .setPositiveButton("Да") { dialog, _ ->
                    //presenter.deleteAll()
                    dialog.cancel()
                }
                .setNegativeButton("Нет") { dialog, _ -> dialog.cancel() }
                .create()
                .show()
    }

    private fun onVersionClick() {
        val title = activity!!.getString(R.string.version)
        val text = activity!!.getString(R.string.about_version)
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("Ок") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
    }

    private fun onSetMarkClick() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=com.bignerdranch.android.OSM")
        startActivity(intent)
    }

    private fun onAuthClick() {
        signOut()
    }

    private fun signOut() {
        AlertDialog.Builder(activity)
                .setTitle("Выход из аккаунта")
                .setMessage("Вы действительно выйти из аккаунта?")
                .setPositiveButton("Да") { dialog, _ ->
                    //settingsService.signOut()

                    dialog.cancel()
                    //router.setRoot(RouterTransaction.with(AuthController()))
                }
                .setNegativeButton("Нет") { dialog, _ -> dialog.cancel() }
                .create()
                .show()
    }
}