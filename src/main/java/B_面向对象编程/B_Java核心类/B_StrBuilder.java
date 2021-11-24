package B_面向对象编程.B_Java核心类;

public class B_StrBuilder {
    public static void main(String[] args) {
        //下述拼接字符串的方法，在每次循环中都会产生一个新的字符串对象，然后扔掉旧的字符串。这样，绝大多数字符串都是临时
        //对象，浪费内存
        String str = "";
        for(int i = 0; i < 1000; i++) {
            str = str + "," + i;
        }
        System.out.println(str);

        //为了解决上述问题，提供了StringBuilder对象，它是一个可变对象，可以预分配缓冲区，新增字符时，不会创建新的临时对象
        //输入参数为字符个数
        StringBuilder stringBuilder = new StringBuilder(1024);
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(",");
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder.getClass());
        System.out.println(stringBuilder.toString().getClass());
    }
}
