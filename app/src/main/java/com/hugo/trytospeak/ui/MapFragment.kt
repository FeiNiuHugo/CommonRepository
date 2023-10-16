package com.hugo.trytospeak.ui

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.hugo.trytospeak.R
import com.hugo.trytospeak.components.indoor.Adapter.DataAdapter
import com.hugo.trytospeak.components.indoor.unit.PathUnit
import com.hugo.trytospeak.data.DataJson
import com.hugo.trytospeak.databinding.FragmentMapBinding
import com.hugo.trytospeak.utils.SpeechData
import com.hugo.trytospeak.utils.SpeechLiveData
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.NLP_RESULT
import com.hugo.trytospeak.utils.SpeechLiveData.Companion.RECOGNIZE_RESULT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MapFragment : Fragment(), Observer<SpeechData> {

    companion object {
        const val TAG = "MapFragment"
        const val DEBUG = false
    }

    private lateinit var viewBinding: FragmentMapBinding

    private val units = mutableListOf<PathUnit>()
    private var adapter = DataAdapter()
    private var bmp: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {
            delay(100)

            // 背景图
            try {
                val opt = BitmapFactory.Options()
                opt.inPreferredConfig = Bitmap.Config.RGB_565
                bmp = BitmapFactory.decodeResource(resources, R.drawable.zxc, opt)
                adapter.setBmp(bmp)
                bmp = null
                units() // 设置数组
                adapter.setList(units) // 设置数组
                adapter.refreshData()
            } finally {
                withContext(Dispatchers.Main) {
                    viewBinding.progressBar.visibility = View.GONE
                }
            }
        }
        viewBinding.indoor.setAdapter(adapter)

        viewBinding.indoor.setOnClickMapListener {
            val dlg = AlertDialog.Builder(activity)
            dlg.setTitle("商店介绍")
            dlg.setMessage(it.name)
            dlg.setPositiveButton("OK") { _, _ ->

            }
            dlg.show()
        }

        SpeechLiveData.get().observe(viewLifecycleOwner, this)
    }

    override fun onChanged(t: SpeechData) {
        when (t.key) {
            RECOGNIZE_RESULT -> {

            }
            NLP_RESULT -> {

            }
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        units.clear()
        adapter.setBmp(null)
        bmp?.apply {
            if (!isRecycled) recycle()
            bmp = null
        }
    }

    // 图案列表
    private fun units() {
        val data = DataJson()
        (0 until data.size()).forEach {
            val jsonObj = data.getArray(it)
            val unit = PathUnit(list(jsonObj))
            try {
                unit.name = jsonObj.optString("name")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            units.add(unit)
        }
    }

    // 每个图案的坐标组合
    private fun list(jsonObj: JSONObject): List<PointF> {
        val points = mutableListOf<PointF>()
        try {
            val density = resources.displayMetrics.density
            val array = jsonObj.optJSONArray("area")
            (0 until (array?.length() ?: 1)).forEach {
                val x = (array!![it] as JSONObject).optInt("x")
                val y = (array[it] as JSONObject).optInt("y")
                points.add(PointF(x * density, y * density))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return points
    }
}