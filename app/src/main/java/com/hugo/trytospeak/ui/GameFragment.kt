package com.hugo.trytospeak.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hugo.trytospeak.databinding.FragmentGameBinding
import com.hugo.trytospeak.utils.SpeechData
import com.hugo.trytospeak.utils.SpeechLiveData
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.NLP_RESULT
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.RECOGNIZE_RESULT

class GameFragment : Fragment(), Observer<SpeechData> {

    companion object {
        const val TAG = "GameFragment"
        const val DEBUG = false
    }

    private lateinit var viewBinding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SpeechLiveData.get().observe(viewLifecycleOwner, this)
    }

    override fun onChanged(t: SpeechData) {
        when (t.key) {
            RECOGNIZE_RESULT -> {
                viewBinding.gameView.handle(t.value[0])
            }
            NLP_RESULT -> {

            }
            else -> {}
        }
    }
}