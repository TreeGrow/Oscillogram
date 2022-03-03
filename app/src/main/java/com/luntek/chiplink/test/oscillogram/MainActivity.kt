package com.luntek.chiplink.test.oscillogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.luntek.chiplink.test.oscillogramlib.ItemBean
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var t1 = byteArrayOf(
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0
    );
    var t2 = byteArrayOf(
        0,
        0,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    );
    var t3 = byteArrayOf(
        0,
        0,
        0,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        1,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        0,
        1,
        0,
        0
    );
    var t4 = byteArrayOf(
        0,
        0,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        0,
        0,
        1,
        1
    );
    var t5 = byteArrayOf(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        1,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    );
    var t6 = byteArrayOf(
        3,
        3,
        3,
        3,
        3,
        3,
        3,
        0,
        0,
        4,
        4,
        4,
        4,
        4,
        4,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        1,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //去掉最上面时间、电量等
        val itemList: MutableList<ItemBean> = ArrayList()
        var item = ItemBean()
        item.array = t1
        item.name = "SRCLK"
        itemList.add(item)
        item = ItemBean()
        item.array = t2
        item.name = "SER"
        itemList.add(item)
        item = ItemBean()
        item.array = t3
        item.name = "RCLK"
        itemList.add(item)
        item = ItemBean()
        item.array = t4
        item.name = "SRCLR"
        itemList.add(item)
        item = ItemBean()
        item.array = t5
        item.name = "OE"
        itemList.add(item)
        item = ItemBean()
        item.array = t6
        item.name = "QA"
        itemList.add(item)

        timing.timingView(itemList)
        timing.txtSize=35;



    }

}