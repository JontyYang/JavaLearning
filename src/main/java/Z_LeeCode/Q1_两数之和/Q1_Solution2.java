package Z_LeeCode.Q1_两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
 */

//时间复杂度O(n), 空间复杂度O(1)
public class Q1_Solution2 {
    public static int[] twoSum(int[] nums, int target) {
        //使用hashMap进行索引、值的存储
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            } else {
                hashMap.put(nums[i], i);
            }
        }
         return new int[]{};
    }
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] result = Q1_Solution2.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
