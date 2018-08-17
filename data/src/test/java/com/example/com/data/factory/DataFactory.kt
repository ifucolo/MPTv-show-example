package com.example.com.data.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {

    fun randowFloat() = Math.random().toFloat()
    fun randowInt() = Math.random().toInt()
    fun randowUuid() = java.util.UUID.randomUUID().toString()

}