package A_Java快速入门.B_Java程序基础;

/**
 * 当定义一个变量时，JVM会为其在内存中分配一个存储单元
 * Java中int、float占用四个字节，double、long占用八个字节
 * char、short两个字节，byte一个字节
 *
 * 对于整型类型，Java只定义了带符号的整型，因此，最高位的bit表示符号位（0表示正数，1表示负数）。各种整型能表示的最大范围如下：
 * byte：-128 ~ 127
 * short: -32768 ~ 32767
 * int: -2147483648 ~ 2147483647
 * long: -9223372036854775808 ~ 9223372036854775807
 *
 * 浮点数可表示的范围非常大，float类型可最大表示3.4x1038，而double类型可最大表示1.79x10308
 *
 * Java语言对布尔类型的存储并没有做规定，因为理论上存储布尔类型只需要1 bit，但是通常JVM内部会把boolean表示为4字节整数
 *
 * 注意char类型使用单引号'，且仅有一个字符，要和双引号"的字符串类型区分开。
 */
public class 变量和数据类型 {
    public static void main(String[] args) {
        /*
        整数运算
         */
        int i = 2147483647;
        int i2 = -2147483648;
        long l = 9000000000000000000L; // long型的结尾需要加L
        float f = 1.0f;

        //除了上述基本类型的变量，剩下的都是引用类型。例如，引用类型最常用的就是String字符串
        //引用内部存储一个“地址”，指向某个对象在内存的位置，后续我们介绍类的概念时会详细讨论
        String s = "Hello";

        //定义变量的时候，如果加上final修饰符，这个变量就变成了常量
        final float PI = 3.1415926f;

        //要特别注意，整数由于存在范围限制，如果计算结果超出了范围，就会产生溢出，而溢出不会出错，却会得到一个奇怪的结果
        int x = 2147483640;
        int y = 15;
        int sum = x + y;
        System.out.println(sum); // -2147483641

        //强制转型（类型）i
        short int1 = 1;
        int int2 = (int)int1;

        //计算前100个数总和
        int n = 100;
        int sum100 = 0;
        while(n>0) {
            sum100 += n;
            n--;
        }
        System.out.println(sum100);

        /*
        浮点数运算
         */
        //由于浮点数存在运算误差，所以比较两个浮点数是否相等常常会出现错误的结果。
        // 正确的比较方法是判断两个浮点数之差的绝对值是否小于一个很小的数
        // 比较x和y是否相等，先计算其差的绝对值:
        double x1 = 1.0 / 10;
        double y1 = 1 - 9.0 / 10;
        double r = Math.abs(x1 - y1);
        // 再判断绝对值是否足够小:
        if (r < 0.00001) {
            // 可以认为相等
        } else {
            // 不相等
        }

        //如果要进行四舍五入，可以对浮点数加上0.5再强制转型：
        double d = 2.6;
        int nd = (int) (d + 0.5);
        System.out.println(nd);

        /*
        浮点数运算
        */
        //布尔运算的一个重要特点是短路运算。如果一个布尔运算的表达式能提前确定结果，则后续的计算不再执行，直接返回结果。
        //因为false && x的结果总是false，无论x是true还是false，因此，与运算在确定第一个值为false后，不再继续计算，而是直接返回false
        //三元运算符 int x = n >= 0 ? n : -n;

        /*
        字符和字符串
        */
        //Java在内存中总是使用Unicode表示字符,一个char保存一个Unicode字符
        char c1 = 'A';
        int a = c1;
        System.out.println(a);
        //字符串转义字符
        String s1 = "123\"123";
        //多行字符串
        String morestring = "123 \n"
                + "234 \n"
                + "345";
        System.out.println(morestring);
        //引用类型的变量可以指向一个空值null，它表示不存在，即该变量不指向任何对象
        //注意要区分空值null和空字符串""，空字符串是一个有效的字符串对象，它不等于null
        String s11 = null;

         /*
         数组类型(引用类型)
         数组大小不可变,按顺序存储
         */
         int[] ns1 = new int[5];
         int[] ns2 = new int[]{1, 2, 3, 4, 5};
         int[] ns3 = {1, 2, 3, 4, 5};

    }

}
