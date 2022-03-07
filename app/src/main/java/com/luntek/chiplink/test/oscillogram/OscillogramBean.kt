package com.luntek.chiplink.test.oscillogram

data class OscillogramBean(
    val `data`: List<Data>
)

data class Data(
    val array: String,
    val name: String
)