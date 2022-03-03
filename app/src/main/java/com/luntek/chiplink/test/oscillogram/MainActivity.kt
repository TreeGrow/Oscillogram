package com.luntek.chiplink.test.oscillogram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luntek.chiplink.test.oscillogramlib.ItemBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val SRCLK = byteArrayOf(
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
    )
    private val SER = byteArrayOf(
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
    )
    private val RCLK = byteArrayOf(
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
    )
    private val SRCLR = byteArrayOf(
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
    )
    private val OE = byteArrayOf(
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
    )
    private val QA = ByteArray(33)
    private val QB = ByteArray(33)
    private val QC = ByteArray(33)
    private val QD = ByteArray(33)
    private val QE = ByteArray(33)
    private val QF = ByteArray(33)
    private val QG = ByteArray(33)
    private val QH1 = ByteArray(33)
    private val QH2 = ByteArray(33)
    private val info = arrayOf(
        "SRCLK",
        "SER",
        "RCLK",
        "SRCLR",
        "OE",
        "QA",
        "QB",
        "QC",
        "QD",
        "QE",
        "QF",
        "QG",
        "QH",
        "QH'"
    )
    private val item = arrayOf(SRCLK, SER, RCLK, SRCLR, OE, QA, QB, QC, QD, QE, QF, QG, QH1, QH2)
    private val test = Array(9) {
        ByteArray(
            33
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //去掉最上面时间、电量等
        val itemList: MutableList<ItemBean> = ArrayList()
        for (a in 0..13) {
            if (a < 5) {
                val itemBean = ItemBean(info[a], item[a])
                itemList.add(itemBean)
            } else {
                val itemBean = ItemBean(info[a], test[a - 5])
                itemList.add(itemBean)
            }
        }
        timing.setTxtSize(30);
        timing.timingView(itemList)



    }

}