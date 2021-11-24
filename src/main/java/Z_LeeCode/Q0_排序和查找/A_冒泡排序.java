package Z_LeeCode.Q0_排序和查找;

import java.util.Arrays;

public class A_冒泡排序 {
    //冒泡排序1
    public static void bubbleSort1(int[] nums) {
        //对于n个数，只需要冒泡n-1轮
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length-1; j++) {
                if(nums[j + 1] < nums[j]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    //冒泡排序2
    public static void bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length -1; i++) {
            for(int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 2, 7, 4, 10, 4, 3};
        A_冒泡排序.bubbleSort1(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {1, 2, 7, 6, 10, 4, 3};
        A_冒泡排序.bubbleSort2(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
