package xianchao.com.practice

import java.util.*

object ArraySort {

    fun bubbleSort(array: Array<Int>) {
        println(Arrays.toString(array))
        var inIndex = 0
        var outIndex = 0
        var temp = 0
        while (outIndex < array.size) {
            inIndex = outIndex + 1
            while (inIndex < array.size) {
                if (array[outIndex] > array[inIndex]) {
                    temp = array[outIndex]
                    array[outIndex] = array[inIndex]
                    array[inIndex] = temp
                }
                println(Arrays.toString(array))
                inIndex++
            }
            outIndex++
        }
    }

}