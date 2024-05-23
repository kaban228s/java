//package вутшы;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//import static java.util.Collections.swap;
//
//public class QuickSort {
//
//
//    int [] array = new int[] {3, 5, 2};
//
//    void sort (int[]arr, int low, int high){
//        if (low >= high) return;
//        int pivot = arr [(high + low)/2];
//        int lIdx = low;
//        int rIdx = high;
//        while (lIdx <= rIdx){
//            while (arr[lIdx] < pivot)lIdx++;
//            while (arr[rIdx] > pivot)rIdx++;
//            if (lIdx <= rIdx) swap(lIdx, rIdx)R++, L++;
//            if (L < rIdx) sort(arr,lIdx, rIdx);
//            if (lIdx < R) sort(arr, lIdx, rIdx);
//        }
//    }
//
//    private void swap(int lIdx, int rIdx) {
//    }
//
//
//}
