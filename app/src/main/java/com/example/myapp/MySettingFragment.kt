package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val idPreference: EditTextPreference? = findPreference("name")
        idPreference?.title = "닉네임 변경"

        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
                preference ->
            val text = preference.text
            if(TextUtils.isEmpty(text)) {
                "닉네임 설정이 되지 않았습니다."
            }
            else {
                "설정된 닉네임은 $text 입니다."
            }
        }

        val regionPreference : ListPreference? = findPreference("region")
        regionPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()


    }



}