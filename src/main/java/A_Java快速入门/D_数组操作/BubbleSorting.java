package A_Java快速入门.D_数组操作;

import java.util.Arrays;

public class BubbleSorting {
    public static void main(String[] args) {
        int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        /**
         * 冒泡排序
         * 冒泡排序的特点是，每一轮循环后，最大的一个数被交换到末尾，
         * 因此，下一轮循环就可以“刨除”最后的数，每一轮循环都比上一轮循环的结束位置靠前一位
         */
        //排序前
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] > ns[j+1]) {
                    int tmp = ns[j];
                    ns[j] = ns[j+1];
                    ns[j+1] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(ns));

        //多维数组
        //二维数组的每个数组元素的长度并不要求相同
        int[][] moreNS = {
                { 1, 2, 4, 3 },
                { 7 ,8, 9 },
                { 5, 6 }
        };
        //Arrays.deepToString()打印数组
        System.out.println(Arrays.deepToString(moreNS));
    }
}
