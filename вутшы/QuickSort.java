package вутшы;

import java.util.Arrays;

public class QuickSort {

    int[] array = new int[]{3, 5, 2};

    void sort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivot = arr[(high + low) / 2];
        int lIdx = low;
        int rIdx = high;
        while (lIdx <= rIdx) {
            while (arr[lIdx] < pivot) lIdx++;
            while (arr[rIdx] > pivot) rIdx--;
            if (lIdx <= rIdx) {
                swap(arr, lIdx, rIdx);
                lIdx++;
                rIdx--;
            }
        }
        if (low < rIdx) sort(arr, low, rIdx);
        if (lIdx < high) sort(arr, lIdx, high);
    }

    private void swap(int[] arr, int lIdx, int rIdx) {
        int temp = arr[lIdx];
        arr[lIdx] = arr[rIdx];
        arr[rIdx] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        System.out.println("Before sorting: " + Arrays.toString(quickSort.array));
        quickSort.sort(quickSort.array, 0, quickSort.array.length - 1);
        System.out.println("After sorting: " + Arrays.toString(quickSort.array));
    }
}
