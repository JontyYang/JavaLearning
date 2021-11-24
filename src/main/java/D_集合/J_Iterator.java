package D_集合;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
Iterator是一种抽象的数据访问模型。使用Iterator模式进行迭代的好处有：

对任何集合都采用同一种访问模型；
调用者对集合内部结构一无所知；
集合类返回的Iterator对象知道如何迭代。
Java提供了标准的迭代器模型，即集合类实现java.util.Iterable接口，返回java.util.Iterator实例
 */
public class J_Iterator {
    public static void main(String[] args) {
        //Java的集合类都可以使用for each循环，List、Set和Queue会迭代每个元素，Map默认会迭代每个key。
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Jon");
        for (String str : map.values()) {
            System.out.println(str);
        }
        //上述代码能够编译通过，只是因为编译器把for each循环通过Iterator改写为了普通的for循环
        //即
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            System.out.println(str);
        }
        /*
        我们把这种通过Iterator对象遍历集合的模式称为迭代器。
        使用迭代器的好处在于，调用方总是以统一的方式遍历各种集合类型，而不必关系它们内部的存储结构。
         */

    }
}
