package B_面向对象编程.A_面向对象基础;


/**
 * 方法重载，如果一个类中有多个方法同名且参数有所不同，则为重载方法
 * 常见为多构造函数
 * 方法重载的返回值类型通常都是相同的
 */
public class B_重载 {
    public static void main(String[] args) {
        String s = "Test string";
        //字符如果是char类型则为unicode字符，如果为int则为unicode编码
        int code = 'A';
        char word = 'A';
        /*
        String提供了多个indexOf()方法，可以查找子串
         */
        //根据字符的Unicode编码进行索引
        int n1 = s.indexOf('t');
        //根据字符串进行索引
        int n2 = s.indexOf("st");
        //根据字符查找，但指定起始位置
        int n3 = s.indexOf('t', 4);
        //根据字符串查找，但指定起始位置
        int n4 = s.indexOf("st", 4);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
    }
}
