package com.mycompany.algorithmsproject;
import java.util.Random;

public class AlgorithmsProject {
    
    public static final Random GLOBAL_RAND = new Random();

    public static void main(String[] args) {

       
        int[] sizes = {1000, 10000, 50000, 100000};

        String[] types = {"Random", "Sorted", "Reverse-Sorted", "Few-Unique"};

        for (int size : sizes) {
            System.out.println("\n+-------------------------------------------------------------+");
            System.out.printf("| Dataset Size: %-45d |\n", size);
            System.out.println("+----------------------+--------------+--------------+--------------+--------------+--------------+");
            System.out.printf("| %-20s | %-12s | %-12s | %-12s | %-12s | %-12s |\n",
                    "Data Type", "Insertion", "Merge", "Heap", "Quick", "RandomQ");
            System.out.println("+----------------------+--------------+--------------+--------------+--------------+--------------+");

            for (String type : types) {

                int[] data;
                if (type.equals("Sorted")) {
                    data = generateSorted(size);
                } else if (type.equals("Reverse-Sorted")) {
                    data = generateReverseSorted(size);
                } else if (type.equals("Few-Unique")) {
                    data = generateFewUnique(size);
                } else {
                    data = generateRandom(size);
                }

                int[] copy;
                long start, end;

                copy = data.clone();
                start = System.nanoTime();
                Sorting.insertionSort(copy);
                end = System.nanoTime();
                double insertionTime = (end - start) / 1_000_000.0;

                copy = data.clone();
                start = System.nanoTime();
                Sorting.mergeSort(copy);
                end = System.nanoTime();
                double mergeTime = (end - start) / 1_000_000.0;

                copy = data.clone();
                start = System.nanoTime();
                Sorting.heapSort(copy);
                end = System.nanoTime();
                double heapTime = (end - start) / 1_000_000.0;

                copy = data.clone();
                start = System.nanoTime();
                Sorting.quickSort(copy);
                end = System.nanoTime();
                double quickTime = (end - start) / 1_000_000.0;

                copy = data.clone();
                start = System.nanoTime();
                Sorting.randomizedQuickSort(copy);
                end = System.nanoTime();
                double randQuickTime = (end - start) / 1_000_000.0;

                System.out.printf(
                        "| %-20s | %-12.3f | %-12.3f | %-12.3f | %-12.3f | %-12.3f |\n",
                        type, insertionTime, mergeTime, heapTime, quickTime, randQuickTime
                );
            }

            System.out.println("+----------------------+--------------+--------------+--------------+--------------+--------------+");
        }
 
    }

   

    public static int[] generateRandom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = GLOBAL_RAND.nextInt(1_000_000) + 1;
        return arr;
    }
public static int[] generateSorted(int n) {
    int[] arr = new int[n];

    int current = GLOBAL_RAND.nextInt(10);
    for (int i = 0; i < n; i++) {
        current += GLOBAL_RAND.nextInt(5);
        arr[i] = current;
    }
    return arr;
}
  

    public static int[] generateReverseSorted(int n) {
        int[] arr = generateSorted(n);
        for (int i = 0; i < n / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = tmp;
        }
        return arr;
    }

    public static int[] generateFewUnique(int n) {
        int[] arr = new int[n];
        int[] unique = {1, 2, 3, 4, 5};
        for (int i = 0; i < n; i++) arr[i] = unique[GLOBAL_RAND.nextInt(unique.length)];
        return arr;
    }
}
