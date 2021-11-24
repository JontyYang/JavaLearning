package I_正则表达式;

public class B_复杂匹配规则 {
    public static void main(String[] args) {
        /*
        匹配开头和结尾
        用正则表达式进行多行匹配时，我们用^表示开头，$表示结尾。例如，^A\d{3}$，可以匹配"A001"、"A380"。

        匹配指定范围
        使用[...]可以匹配范围内的字符，例如，[123456789]可以匹配1~9，这样就可以写出上述电话号码的规则：[123456789]\d{6,7}
        把所有字符全列出来太麻烦，[...]还有一种写法，直接写[1-9]就可以。
        要匹配大小写不限的十六进制数，比如1A2b3c，我们可以这样写：[0-9a-fA-F]，它表示一共可以匹配以下任意范围的字符：
        0-9：    字符0~9；
        a-f：    字符a~f；
        A-F：    字符A~F。
        如果要匹配6位十六进制数，前面讲过的{n}仍然可以继续配合使用：[0-9a-fA-F]{6}

        [...]还有一种排除法，即不包含指定范围的字符。假设我们要匹配任意字符，但不包括数字，可以写[^1-9]{3}：
        可以匹配"ABC"，因为不包含字符1~9；
        可以匹配"A00"，因为不包含字符1~9；
        不能匹配"A01"，因为包含字符1；
        不能匹配"A05"，因为包含字符5。
         */
        String str1 = "ABc123";
        String regex1 = "^\\w{3}123$";
        System.out.println(str1.matches(regex1));

        /*
        或规则匹配
        用|连接的两个正则规则是或规则，例如，AB|CD表示可以匹配AB或CD
         */
        String regex2 = "php|java";
        System.out.println("java".matches(regex2));
        System.out.println("php".matches(regex2));
        System.out.println("jj".matches(regex2));

        /*
        空格或者制表符也可直接打印出来进行匹配，不一定用\s
         */
        String str2 = "1 2";
        String regex3 = "\\d \\d";
        System.out.println(str2.matches(regex3));

        /*
        使用括号
        现在我们想要匹配字符串learn java、learn php和learn go怎么办？
        一个最简单的规则是learn\sjava|learn\sphp|learn\sgo，但是这个规则太复杂了，可以把公共部分提出来，
        然后用(...)把子规则括起来表示成learn\\s(java|php|go)。
         */
        String regex4 = "learn\\s(java|go|php)";
        System.out.println("learn java".matches(regex4));
        System.out.println("learn go".matches(regex4));
        System.out.println("learn c++".matches(regex4));
    }
}
