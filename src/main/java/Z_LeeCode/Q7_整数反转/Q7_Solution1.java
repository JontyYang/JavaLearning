package Z_LeeCode.Q7_整数反转;

/*
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
假设环境不允许存储 64 位整数（有符号或无符号）。
 
示例 1：
输入：x = 123
输出：321

示例 2：
输入：x = -123
输出：-321

示例 3：
输入：x = 120
输出：21

示例 4：
输入：x = 0
输出：0
 
提示：
-2^31 <= x <= 2^31 - 1

 */
public class Q7_Solution1 {
    /*
    假设环境不允许存储 64 位整数（有符号或无符号）
    这个地方注意：32位的数字如果溢出，其值将不是原来的值。
    如果有大于32位（四字节）的数，则存储在64位中（8字节）
     */
    public int reverse(int x) {
        //输出结果
        int result = 0;
        //商
        int div = x;
        //余数
        int mod = 0;
        int tmp = 0;
        while(true) {
            //获取余数
            mod = div % 10;
            //获取商
            div = div / 10;
            //设定临时值tmp，存储上一次result
            tmp = result;
            result = (result * 10) + mod;
            //当商为0时，循环结束
            if (div == 0) {
                //如果更新后的result值除10后的商和上一次result值不一致，则说明溢出
                if ((result / 10) != tmp) {
                    //注意此时result的值已经溢出错误，除了java，在许多语言中会不通过。
                    return 0;
                } else {
                    return result;
                }
            }

        }
    }

    public static void main(String[] args) {
        Q7_Solution1 q7_solution1 = new Q7_Solution1();
        System.out.println(q7_solution1.reverse(-2147483412));

    }
}
