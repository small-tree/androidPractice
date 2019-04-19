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


    fun quickSork(array: Array<Int>) {

        println(Arrays.toString(array))
        var pivot = array[0]
        var start = 1
        var end = array.size - 1
        while (start < end) {
            while (start < end && pivot <= array[end]) {
                end--
            }
            if (pivot >= array[end]){
                var temp = array[end]
                array[end] = pivot
                pivot = temp
            }

            while (start <end && pivot >= array[start]){
                start ++
            }

            if (pivot <= array[start]){
                var temp = array[start]
                array[start] = pivot
            }


        }


        println(Arrays.toString(array))

    }


}