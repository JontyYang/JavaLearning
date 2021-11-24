package Z_LeeCode.Q7_整数反转;

public class Q7_Solution2 {
    public int reverse(int x) {
        int result = 0;
        int tmp = 0;
        while (x !=  0) {
            tmp = result;
            result = (result * 10) + (x % 10);
            x = x / 10;
        }
        if (tmp != (result / 10)) {
            return 0;
        } else {
            return result;
        }

    }
    public static void main(String[] args) {
        Q7_Solution1 q7_solution1 = new Q7_Solution1();
        System.out.println(q7_solution1.reverse(-2147483419));
    }
}
