package com.dicoding.habitapp.setting


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.*
import com.dicoding.habitapp.R
import java.util.*
import kotlin.system.exitProcess


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            // 11 : Update theme based on value in ListPreference
            val themePrefList = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            themePrefList?.setOnPreferenceChangeListener{_, newValue ->
                when(newValue){
                    getString(R.string.pref_dark_follow_system) -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    getString(R.string.pref_dark_on) -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    getString(R.string.pref_dark_off) -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    else -> true
                }
            }

        }


        private fun updateTheme(mode: Int): Boolean  {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }


    }


}