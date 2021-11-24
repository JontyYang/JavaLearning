package B_面向对象编程.B_Java核心类;

import java.util.Arrays;

//字符串操作不改变原字符串内容，而是返回新字符串

public class A_字符串和编码 {
    public static void main(String[] args) {
        String s1 = "HELLO";
        String s2 = "hello".toUpperCase();
        String s3 = " Hello, Jonty !!!  1\u3000";
        //比较字符串引用
        System.out.println(s1 == s2);
        //比较字符串内容
        System.out.println(s1.equals(s2));
        //忽略大小写比较字符串内容
        System.out.println(s1.equalsIgnoreCase(s2));
        //字符串是否包含子串
        System.out.println(s1.contains("He"));
        //判断字符的第一个索引位置
        System.out.println(s1.indexOf('L'));
        //判断字符最后一个索引位置
        System.out.println(s1.lastIndexOf('L'));
        //判断字符串是否以某个字符串开始
        System.out.println(s1.startsWith("HE"));
        //判断字符串是否以某个字符串结束
        System.out.println(s1.endsWith("LO"));
        //提取子串
        System.out.println(s1.substring(2));
        System.out.println(s1.substring(2, 4));

        //去除首尾空白字符（\t, \r, \n）
        //trim方法并没有改变字符串内容，而是返回了一个新的字符串
        System.out.println(s3.trim());
        //strip()也可以去除首尾空白字符，它比trim方法多去除中文空格字符\u3000
        System.out.println(s3.strip());
        System.out.println(s3.stripLeading());
        System.out.println(s3.stripTrailing());

        //isEmpty()判断字符串是否为空
        //isBlank()判断字符串是否为空白字符串
        //空字符串一定是空白字符串
        System.out.println("".isEmpty());
        System.out.println("    ".isEmpty());
        System.out.println("".isBlank());
        System.out.println("   ".isBlank());
        System.out.println("jkjkl".isBlank());

        //通过字符或者字符串替换子串
        String s4 = "Jonty";
        System.out.println(s4.replace('J', 'Y'));
        System.out.println(s4.replace("Jon", "Yan"));

        //分割字符串,split生成一个字符串数组
        String s5 = "1,2,3,4";
        System.out.println(Arrays.toString(s5.split(",")));

        //拼接字符串,生成一个字符串
        String[] str = {"2", "you", "jj"};
        System.out.println(String.join("---", str));

        //类型转换,其它类型数据转换为字符串
        System.out.println(String.valueOf(123));
        System.out.println(String.valueOf(34.78));
        char[] cha1r = {'1', '2'};
        System.out.println(String.valueOf(cha1r));
        //字符串转换为其他类型
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("ff", 16));
        //getInteger(String)方法，不是直接将字符串转为int，而是转为Integer
        System.out.println(Integer.getInteger("1bo0"));

        int[] int1 = {1, 2, 3};
        System.out.println(int1);
        System.out.println(Arrays.toString(int1));
        //字符数组和字符串互相转换
        char[] ch = "Hello".toCharArray();
        char[] ch1 = {'H', 'e', 'l', 'l', 'o'};
        System.out.println(ch);
        System.out.println(ch1);
        System.out.println(Arrays.toString(ch));
        System.out.println(Arrays.toString(ch1));
        //判断两个字符数组相等
        System.out.println(Arrays.equals(ch1, ch));
        String s = new String(ch);
        System.out.println(s);
        ch[0] = 'T';
        //对于字符串而言，要求是不能改变的，因此对于字符串的赋值，参数传的是字符数组的复制，而不是引用，因此不发生改变，保证了字符串的不变
        System.out.println(ch);
        System.out.println(s);

        //字符数组是引用类型，传的是引用，因此外部发生改变，内部也改变
        char[] chch = {'1', '2', '5'};
        System.out.println(chch);
        Ren ren = new Ren(chch);
        ren.printInfo();    //115
        chch[0] = '2';
        System.out.println(chch);
        ren.printInfo();   //225

    }

}

class Ren {
    private char[] ch;

    public Ren() {
    }

    public Ren(char[] ch) {
        this.ch = ch;
    }

    public void printInfo() {
        System.out.println(this.ch);
    }
}
