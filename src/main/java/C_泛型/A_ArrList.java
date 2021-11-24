package C_泛型;

//ArrayList，它可以看作“可变长度”的数组，因为用起来比数组更方便
//实际上ArrayList内部就是一个Object[]数组，配合存储一个当前分配的长度，就可以充当“可变数组”

import java.util.ArrayList;
import java.util.List;

/**
 * public class ArrayList {
 *     private Object[] array;
 *     private int size;
 *     public void add(Object e) {...}
 *     public void remove(int index) {...}
 *     public Object get(int index) {...}
 * }
 *
 * 如果用上述ArrayList存储String类型，会有这么几个缺点：
 * 需要强制转型；
 * 不方便，易出错
 * ArrayList list = new ArrayList();
 * list.add("Hello");
 * // 获取到Object，必须强制转型为String:
 * String first = (String) list.get(0);
 *
 * ClassCastException，因为容易“误转型”
 * list.add(new Integer(123));
 * // ERROR: ClassCastException:
 * String second = (String) list.get(1);
 */
public class A_ArrList {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Integer(123));
        Integer integer = (Integer) arrayList.get(0);
        System.out.println(String.valueOf(integer));

        /**
         * 为了解决上述问题，提出了模板类
         * public class ArrayList<T> {
         *     private T[] array;
         *     private int size;
         *     public void add(T e) {...}
         *     public void remove(int index) {...}
         *     public T get(int index) {...}
         * }
         * T可以是任何class。这样一来，我们就实现了：编写一次模版，可以创建任意类型的ArrayList
         * // 创建可以存储String的ArrayList:
         * ArrayList<String> strList = new ArrayList<String>();
         * // 创建可以存储Float的ArrayList:
         * ArrayList<Float> floatList = new ArrayList<Float>();
         * // 创建可以存储Person的ArrayList:
         * ArrayList<Person> personList = new ArrayList<Person>();
         */

        //在Java标准库中的ArrayList<T>实现了List<T>F_接口，它可以向上转型为List<T>
        List<String> list = new ArrayList<String>();
        list.add("Hello");
        System.out.println(list.get(0));

    }
}
