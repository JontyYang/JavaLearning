package I_正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配默认使用贪婪匹配，可以使用?表示对某一规则进行非贪婪匹配。
 *
 */
public class D_非贪婪匹配 {
    public static void main(String[] args) {
        /*
        给定一个字符串表示的数字，判断该数字末尾0的个数。例如：
        "123000"：   3个0
        "10100"：    2个0
        "1001"：     0个0
        可以很容易地写出该正则表达式：(\d+)(0*)
        然而因为正则表达式默认使用贪婪匹配，(\d+)直接将所有数字进行匹配

        为了使](\d+)尽量少匹配，在其后面添加？表示非贪婪匹配
         */
        Pattern pattern1 = Pattern.compile("(\\d+)(0*)");
        Matcher matcher1 = pattern1.matcher("1230000");
        if (matcher1.matches()) {
            System.out.println(matcher1.group(1));
            System.out.println(matcher1.group(2));
        } else {
            System.out.println("???");
        }

        Pattern pattern2 = Pattern.compile("(\\d+?)(0*)");
        Matcher matcher2 = pattern2.matcher("1230000");
        if (matcher2.matches()) {
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
        } else {
            System.out.println("!!!");
        }

        /*
        我们再来看这个正则表达式(\d??)(9*)，注意\d?表示匹配0个或1个数字，后面第二个?表示非贪婪匹配，因此，给定字符串"9999"，
        匹配到的两个子串分别是""和"9999"，因为对于\d?来说，可以匹配1个9，也可以匹配0个9，但是因为后面的?表示非贪婪匹配，
        它就会尽可能少的匹配，结果是匹配了0个9。
         */
    }
}
