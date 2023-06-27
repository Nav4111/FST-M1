package activities;

import java.util.Arrays;

public class Activity2 {

    public static void main(String[] args) {
        int[] arr = {10,77,10,54,-11,10};
        System.out.println("Original Array: " + Arrays.toString(arr));

        int search = 10;
        int sum = 30;

        System.out.println("Result: " + result(arr,search,sum));
    }
    public static boolean result(int[] nums, int search, int sum ){
        int tempSum=0;
        for (int num: nums) {
            if (num==search){
                tempSum+=num;
            }
            if (tempSum>sum){
                break;
            }
        }
        return tempSum==sum;
    }
}
