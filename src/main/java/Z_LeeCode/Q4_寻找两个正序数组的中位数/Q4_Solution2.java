package Z_LeeCode.Q4_寻找两个正序数组的中位数;

//时间复杂度o（m+n）， 空间复杂度o（1)
public class Q4_Solution2 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        //当俩数组长度为偶数或者奇数时，其所需计算中位数的值最大索引
        int maxMedianIndex = totalLength / 2;
        //数组的索引
        int index1 = 0;
        int index2 = 0;
        //如果是偶数（pre+last）/2.0
        //如果是奇数 last
        int pre = 0, last = 0;
        for (int i = 0; i <= maxMedianIndex; i++) {
            //始终保持pre是last上一个值
            pre = last;
            //如果数组一下标不越界并且（数组一此时值小于数组二此时值或者数组二越界）
            //(index2 >= length2 || nums1[index1] <= nums2[index2]),一定按这个顺序写，
            //只有当第一个条件不满足时，nums2中的下标才可以访问，不然会数组越界
            if (index1 < length1 && (index2 >= length2 || nums1[index1] <= nums2[index2])) {
                last = nums1[index1];
                index1++;
            } else {
                last = nums2[index2];
                index2++;
            }
        }
        if (totalLength % 2 == 0) {
            return (pre +last) / 2.0;
        } else {
            return (double)last;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {2};
        int[] nums2 = {};
        double result = Q4_Solution1.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
