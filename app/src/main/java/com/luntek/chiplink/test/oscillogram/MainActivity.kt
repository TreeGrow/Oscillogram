package com.luntek.chiplink.test.oscillogram

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luntek.chiplink.test.oscillogramlib.ItemBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val itemList: MutableList<ItemBean> = ArrayList()
    val itemList2: MutableList<ItemBean> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oscillogram: OscillogramBean = Gson().fromJson(
            Util.getJson("OscillogramData.json", this),
            object : TypeToken<OscillogramBean?>() {}.type
        )

        for (element in oscillogram.data) {
            var strdata = element.array.split(",")
            val results = ByteArray(strdata.size) { strdata[it].toByte() }
            val itemBean = ItemBean(element.name, results)
            itemList.add(itemBean)
        }

        val oscillogram2: OscillogramBean = Gson().fromJson(
            Util.getJson("OscillogramData2.json", this),
            object : TypeToken<OscillogramBean?>() {}.type
        )

        for (element in oscillogram2.data) {
            var strdata = element.array.split(",")
            val results = ByteArray(strdata.size) { strdata[it].toByte() }
            val itemBean = ItemBean(element.name, results)
            itemList2.add(itemBean)
        }
        timing.setTimingList(itemList, itemList2)

    }

}