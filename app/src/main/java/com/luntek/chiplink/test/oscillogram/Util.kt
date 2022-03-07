package com.luntek.chiplink.test.oscillogram

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


object Util {
    var lastClickTime = 0;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 读取assets下的json文件
     */
    fun getJson(fileName: String?, context: Context): String {
        //将json数据变成字符串
        val stringBuilder = StringBuilder()
        try {
            //获取assets资源管理器
            val assetManager = context.assets
            //通过管理器打开文件并读取
            val bf = BufferedReader(
                InputStreamReader(
                    assetManager.open(fileName!!)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }


    /**
     * 没细化到具体view，只测试使用。
     */
    fun isFastDoubleClick(): Boolean {
        val time = System.currentTimeMillis()
        val timeD: Long = time - lastClickTime
        if (0 < timeD && timeD < 700) {
            return true
        }
        lastClickTime = time.toInt()
        return false
    }


}