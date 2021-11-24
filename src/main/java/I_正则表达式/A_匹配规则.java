package I_正则表达式;

import java.util.List;

/**
 * 正则表达式实际上就是一个表达规则的字符串，
 * java.util.regex中内置了该正则表达式引擎。
 *
 *单个字符的匹配规则如下：
 正则表达式	           规则	                 可以匹配
 A	                 指定字符	                A
 \u548c	           指定Unicode字符           	和
 .	                  任意字符	           a，b，&，0
 \d	                    数字0~9	               0~9
 \w	              大小写字母，数字和下划线	a~z，A~Z_Supplement，0~9，_
 \s	                  空格、Tab键	         空格，Tab
 \D	                     非数字	            a，A，&，_，……
 \W	                     非\w	              &，@，中，……
 \S	                     非\s	               a，A，&，_，……

 多个字符的匹配规则如下：
 正则表达式	             规则	               可以匹配
 A*	                 任意个数字符     	空，A，AA，AAA，……
 A+	                 至少1个字符	       A，AA，AAA，……
 A?	                0个或1个字符	           空，A
 A{3}              	指定个数字符              	AAA
 A{2,3}	           指定范围个数字符          	AA，AAA
 A{2,}	             至少n个字符	            AA，AAA，AAAA，……
 A{0,3}	             最多m个字符	             空，A，AA，AAA
 */
public class A_匹配规则 {
    public static void main(String[] args) {
        /*
        如果正则表达式中有特殊字符，那就需要用\(右斜杠)进行转义，例如，正则表达式a\&c，其中\&是用来匹配特殊字符&的
        注意正则表达式在java中也是一个字符串，所以，对于正则表达式a\&c来说，对应字符串应该是"a\\&c"

        如果想匹配非ASCII字符，例如中文，那就用"\U(U小写)####"的十六进制表示，例如：a\u548cc匹配字符串"a和c"，中文字符和的Unicode编码是548c。
        */
        String regex1 = "abc";
        //验证字符串是否符合正则表达式的规则
        System.out.println("abc".matches(regex1));
        System.out.println("abcd".matches(regex1));
        System.out.println("Abc".matches(regex1));

        String regex2 = "a\\&c";
        System.out.println("a&c".matches(regex2));
        System.out.println("\\&");

        String regex3 = "a\\u548cc";
        System.out.println(regex3);
        System.out.println("a和c".matches(regex3));
        /*
        精确匹配实际上用处不大，因为我们直接用String.equals()就可以做到。大多数情况下，我们想要的匹配规则更多的是模糊匹配。
        1. 可以用.匹配一个任意字符。
        2. 用.可以匹配任意字符，这个口子开得有点大。如果我们只想匹配0~9这样的数字，可以用\d匹配
        3. 用\w可以匹配一个字母、数字或下划线
        4. 用\s可以匹配一个空格字符，注意空格字符不但包括空格，还包括tab字符（在Java中用\t表示）
        5. 用\d可以匹配一个数字，而\D则匹配一个非数字
        6. \W可以匹配\w不能匹配的字符，\S可以匹配\s不能匹配的字符
        7. 修饰符*可以匹配任意个字符，包括0个字符
        8. 修饰符+可以匹配至少一个字符
        9. 修饰符?可以匹配0个或一个字符
        10. 如果我们想精确指定n个字符怎么办？用修饰符{n}就可以
        11. 如果我们想指定匹配n~m个字符怎么办？用修饰符{n,m}就可以
        12. 如果没有上限，那么修饰符{n,}就可以匹配至少n个字符。
         */
        String re = "\\d{3,4}\\-\\d{7,8}";
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println(s);
                System.out.println("测试失败: " + s);
            }
        }

        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
            }
        }

        String regex4 = "\\S";
        if ("和".matches(regex4)) {
            System.out.println("ccc");
        }
    }
}
