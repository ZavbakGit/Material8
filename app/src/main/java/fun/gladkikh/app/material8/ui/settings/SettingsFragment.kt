package `fun`.gladkikh.app.material8.ui.settings

import `fun`.gladkikh.app.material8.MainActivity
import `fun`.gladkikh.app.material8.R
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
    private var indexTheme: Int? = null

    override fun onPreferenceChange(p0: Preference?, value: Any?): Boolean {
        (value as? String)?.toIntOrNull().let {
            indexTheme = it
        }
        return true
    }


    override fun onCreatePreferences(saveInstantState: Bundle?, rootkey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootkey)
        findPreference<ListPreference>("listTheme")!!.onPreferenceChangeListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        indexTheme?.let {
            (activity as MainActivity).themeIndexLd.value = indexTheme
        }
    }

}