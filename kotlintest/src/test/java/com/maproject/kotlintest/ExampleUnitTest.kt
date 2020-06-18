package com.maproject.kotlintest

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun test() {
        var job = GlobalScope.launch {
            delay(1000)

            println("${Thread.currentThread().name}:: nihao nihao")
        }

        runBlocking {
            delay(2000)
            println("${Thread.currentThread().name}::finish")
        }
    }


    @Test
    fun testUn() {
        var job = GlobalScope.launch {
            delay(1000)

            println("${Thread.currentThread().name}:: nihao nihao")
        }
//        job.join()

    }


    /**
     * 协程层级关系
     */
    @Test
    fun test3() {
        runBlocking {
            delay(1000)
            println(this)
            launch {
                delay(1000)
                println(this)
            }
        }
    }


    fun test4() {
        runBlocking {

            coroutineScope {}
        }
    }


    @Test
    fun test5() {

        fun foo(): Flow<Int> = flow {
            // 流构建器
            for (i in 1..3) {
                delay(100) // 假装我们在这里做了一些有用的事情
                emit(i) // 发送下一个值
            }
        }

        fun main() = runBlocking<Unit> {
            // 启动并发的协程以验证主线程并未阻塞
            launch {
                for (k in 1..3) {
                    println("I'm not blocked $k")
                    delay(100)
                }
            }
            // 收集这个流
            foo().collect { value ->
                println(value)
            }
        }

        main()
    }


}
