package Z_LeeCode.Q3_无重复的最长子串;
/*
给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

示例 4:
输入: s = ""
输出: 0

提示：
0 <= s.length <= 5 * 10^4
s 由英文字母、数字、符号和空格组成
 */

import java.util.HashSet;
import java.util.Set;

public class Q3_Solution1 {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;  //无重复最长子串长度
        int right = 0; //滑动窗口的右指针
        Set<Character> hashSet = new HashSet<>();    //用来存储无重复的子串

        //对字符串进行遍历
        for (int i = 0; i < s.length(); i++) {
            //当i不为零时，每执行一次循环，窗口的左指针向右移动一位，因此set删除左指针的字母。
            if (i != 0) {
                hashSet.remove(s.charAt(i - 1));
            }

            while (right < s.length() && !hashSet.contains(s.charAt(right))) {
                hashSet.add(s.charAt(right));
                right++;
            }

            maxLength = Math.max(hashSet.size(), maxLength);

            //如果right的值已经等于字符串长度，说明右指针已经遍历完了整个字符串，则没必要在进行左指针的遍历。
            if (right == s.length()) {
                break;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        Q3_Solution1 q3_solution1 = new Q3_Solution1();
        System.out.println(q3_solution1.lengthOfLongestSubstring(s));

    }

}
