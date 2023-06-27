package activities;

import java.util.Arrays;

public class Activity4 {

    public static void main(String[] args) {
        int[] nums = {67,15,89,25,7,122,56};
        System.out.println(Arrays.toString(nums));
        insertionSort(nums);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(nums));

    }
    static void insertionSort(int[] arr){
        int size = arr.length;
        System.out.println(size);
        for (int i = 1; i < size; i++) {
            int key = arr[i];
            int j=i-1;
            while (j >=0 && arr[j] > key){
                    arr[j+1]=arr[j];
                    j--;
            }
            arr[j+1]=key;
        }
    }
}
