package G_反射;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class E_获取继承关系 {
    public static void main(String[] args) {
        //获取父类信息
        Class class1 = Integer.class;
        Class class2 = class1.getSuperclass();
        System.out.println(class2);
        Class class3 = class2.getSuperclass();
        System.out.println(class3);

        //获取接口信息,getInterfaces()只返回当前类直接实现的接口类型，
        // 并不包括其父类实现的接口类型,获取接口的父接口要用getInterfaces()
        Class[] is = class1.getInterfaces();
        for (Class cls : is) {
            System.out.println(cls);
        }

        /*
        当我们判断一个实例是否是某个类型时，正常情况下，使用instanceof操作符：
        如果是两个Class实例，要判断一个向上转型是否成立，可以调用isAssignableFrom()：
         */

    }
}
