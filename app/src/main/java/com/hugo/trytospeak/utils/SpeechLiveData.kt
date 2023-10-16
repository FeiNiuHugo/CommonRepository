package com.hugo.trytospeak.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import com.hugo.lib.speech.chatsdk.BaseSpeechCallback
import com.hugo.lib.speech.chatsdk.SpeechManager

data class SpeechData(val key: String, val value: List<String>)

class SpeechLiveData : LiveData<SpeechData>() {

    private val speechCallback = object : BaseSpeechCallback() {

        override fun recognizeResult(text: String?) {
            if (text.isNullOrBlank()) return
            value = SpeechData(RECOGNIZE_RESULT, listOf(text))
        }

        override fun nlpResult(text: String?, json: String?) {
            if (text.isNullOrBlank() || json.isNullOrBlank()) return
            value = SpeechData(NLP_RESULT, listOf(text, json))
        }
    }

    override fun onActive() {
        SpeechManager.getInstance().setBaseSpeechCallback(speechCallback)
    }

    override fun onInactive() {
        SpeechManager.getInstance().setBaseSpeechCallback(null)
    }

    companion object {
        const val RECOGNIZE_RESULT = "recognize_result"
        const val NLP_RESULT = "nlp_result"

        private lateinit var sInstance: SpeechLiveData

        @MainThread
        fun get(): SpeechLiveData {
            sInstance = if (::sInstance.isInitialized) sInstance else SpeechLiveData()
            return sInstance
        }
    }
}