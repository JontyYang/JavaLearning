package I_正则表达式;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分割字符串：String.split()
 * 搜索子串：Matcher.find()
 * 替换字符串：String.replaceAll()
 */
public class E_搜索和替换 {
    public static void main(String[] args) {
        /*
        分割字符串
        使用正则表达式分割字符串可以实现更加灵活的功能。String.split()方法传入的正是正则表达式。
         */
        System.out.println(Arrays.toString("a b c".split("\\s")));
        System.out.println(Arrays.toString("a b  c".split("\\s")));
        System.out.println(Arrays.toString("a  , b  ;;c".split("[\\s\\;\\,]+")));

        /*
        搜索字符串
        我们获取到Matcher对象后，不需要调用matches()方法（因为匹配整个串肯定返回false），而是反复调用find()方法，
        在整个串中搜索能匹配上\\wo\\w规则的子串，并打印出来。这种方式比String.indexOf()要灵活得多，
        因为我们搜索的规则是3个字符：中间必须是o，前后两个必须是字符[A-Za-z0-9_]。
         */
        String string1 = "the quick brown fox jumps over the lazy dog.";
        Pattern pattern1 = Pattern.compile("\\wo\\w");
        Matcher matcher1 = pattern1.matcher(string1);
        while (matcher1.find()) {
            //start和end函数返回值是符合该规则子串的开始、结束位置
            System.out.println(matcher1.start() + "   " + matcher1.end());
            String sub = string1.substring(matcher1.start(), matcher1.end());
            System.out.println(sub);
        }

        /*
        替换字符串
        使用正则表达式替换字符串可以直接调用String.replaceAll()，它的第一个参数是正则表达式，第二个参数是待替换的字符串。
         */
        String s = "the     quick\t\t brown   fox  jumps   over the  lazy dog.";
        //replace方法只能替换指定字符,string的所有替换方法均是返回一个新的字符串
        String r = s.replaceAll("\\s+", " ");
        System.out.println(r);

        /*
        反向引用
        如果我们要把搜索到的指定字符串按规则替换，比如前后各加一个<b>xxxx</b>，这个时候，使用replaceAll()的时候，
        我们传入的第二个参数可以使用$1、$2来反向引用匹配到的子串。例如
         */
        String s1 = "The quick old brown fox jumps over the lazy dog.";
        String r1 = s1.replaceAll("\\s([a-z]){4}\\s", " <b>$1<b> ");
        System.out.println(r1);
    }
}
