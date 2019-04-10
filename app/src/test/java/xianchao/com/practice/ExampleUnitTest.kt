package xianchao.com.practice

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var intArray = arrayOf(3,4,2,1,5,3,6,1)

    @Test
    fun addition_isCorrect() {
        TestJavaCode.test()
        assertEquals(4, 2 + 2)


        ArraySort.bubbleSort(intArray)
    }


}
