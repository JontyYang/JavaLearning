package Z_LeeCode.Q4_寻找两个正序数组的中位数;

import java.util.Arrays;

/*
4. 寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

示例 3：
输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000

示例 4：
输入：nums1 = [], nums2 = [1]
输出：1.00000

示例 5：
输入：nums1 = [2], nums2 = []
输出：2.00000

提示：
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-10^6 <= nums1[i], nums2[i] <= 10^6

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */

//暴力解法，将两个数组的元素放入一个数组之后进行排序，选取中位数，
//时间复杂度m+n，空间复杂度m+n
public class Q4_Solution1 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int index = nums1.length;
        //新的数组num，大小为m+n
        int[] num = new int[nums1.length + nums2.length];
        for (int i =0; i < nums1.length; i++) {
            num[i] = nums1[i];
        }
        for (int j = 0; j < nums2.length; j++) {
            num[index] = nums2[j];
            index++;
        }
        Arrays.sort(num);
        if (num.length % 2 == 0) {
            return (num[num.length / 2] + num[(num.length / 2) -1 ]) / 2.0;
        } else {
            return (double)num[num.length / 2];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {2, 7};
        double result = Q4_Solution1.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
