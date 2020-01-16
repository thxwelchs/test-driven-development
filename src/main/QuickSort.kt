package main

class QuickSort {

    fun sort(arr: Array<Int>) {
        sort(arr, 0, arr.size - 1)
    }

    fun sort(arr: Array<Int>, start: Int, end: Int) {
        val partition = partition(arr, start, end)
        if(start < partition - 1) sort(arr, start, partition - 1)
        if(partition < end) sort(arr, partition, end)
    }

    fun partition(arr: Array<Int>, _start: Int, _end: Int): Int {
//        var start = _start
//        var end = _end
//        val pivot = arr[(start + end) / 2]
//        while(start <= end) {
//            while(arr[start] < pivot) start++
//            while(arr[end] > pivot) end--
//            if(start <= end) {
//                swap(arr, start, end)
//                start++
//                end--
//            }
//        }
        return -1
    }

}