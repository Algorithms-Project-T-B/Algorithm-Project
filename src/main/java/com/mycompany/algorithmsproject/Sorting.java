package com.mycompany.algorithmsproject;

import java.util.Random;

public class Sorting {

    private static final Random rand = new Random();


    public static void randomizedQuickSort(int[] arr) {
        randomizedQuickSort(arr, 0, arr.length - 1);
    }

    private static void randomizedQuickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pi = Partition(arr, low, high);

            if (pi - low < high - (pi + 1)) {
                randomizedQuickSort(arr, low, pi);
                low = pi + 1;
            } else {
                randomizedQuickSort(arr, pi + 1, high);
                high = pi;
            }
        }
    }

    private static int Partition(int[] arr, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1); 
        int pivot = arr[pivotIndex];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do { j--; } while (arr[j] > pivot);
            do { i++; } while (arr[i] < pivot);

            if (i < j) {
                swap(arr, i, j);
            } else {
                return j;
            }
        }
    }


    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pi = partition(arr, low, high);

            if (pi == low) {
                low = pi + 1;
            } else if (pi == high) {
                high = pi - 1;
            } else {
                if (pi - low < high - pi) {
                    quickSort(arr, low, pi - 1);
                    low = pi + 1;
                } else {
                    quickSort(arr, pi + 1, high);
                    high = pi - 1;
                }
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
