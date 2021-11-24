package B_面向对象编程.B_Java核心类;

public class D_包装类型 {
    public static void main(String[] args) {
        //想要把int基本类型变成一个引用类型，我们可以定义一个Integer类，
        // 它只包含一个实例字段int，这样，Integer类就可以视为int的包装类
        //Java核心库为每种基本类型都提供了对应的包装类型,
        //所有的包装类型都是不变类，因为属性那一列为final值
        /**
         * boolean	java.lang.Boolean
         * byte	    java.lang.Byte
         * short	java.lang.Short
         * int	    java.lang.Integer
         * long	   java.lang.Long
         * float	java.lang.Float
         * double	java.lang.Double
         * char	    java.lang.Character
         */
        System.out.println(Integer.valueOf("123").intValue());
        System.out.println(Integer.parseInt("123"));

        //自动装箱、自动拆箱的JDK》=15
        Integer n = 100; // 编译器自动使用Integer.valueOf(int)
        int x = n; // 编译器自动使用n.intValue()
        //引用类型的比较应该用equals而不是==
        System.out.println(Integer.valueOf(100).equals(n));
        //把Integer转为指定进制的字符串
        System.out.println(Integer.toString(100, 8));
        System.out.println(Integer.toOctalString(100));
    }
}
