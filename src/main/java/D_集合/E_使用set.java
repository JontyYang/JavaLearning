package D_集合;

import java.util.HashSet;
import java.util.Set;

/*
    Set用于存储不重复的元素集合，它主要提供以下几个方法：

    将元素添加进Set<E>：boolean add(E e)
    将元素从Set<E>删除：boolean remove(Object e)
    判断是否包含元素：boolean contains(Object e)
 */
public class E_使用set {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(set.add("Jonty"));
        System.out.println(set.add("NiHao"));
        System.out.println(set.add("Jonty"));
        System.out.println(set.size());
        System.out.println(set.contains("Hello"));
        System.out.println(set.remove("Jon"));
        System.out.println(set.contains("Jonty"));
        System.out.println(set.remove("Jonty"));
        System.out.println(set.size());

        /*
        Set实际上相当于只存储key、不存储value的Map。我们经常用Set用于去除重复元素。
        因为放入Set的元素和Map的key类似，都要正确实现equals()和hashCode()方法，否则该元素无法正确地放入Set。
        最常用的Set实现类是HashSet，实际上，HashSet仅仅是对HashMap的一个简单封装

        SortedSet继承了Set接口，TreeSet是SortedSet实现类，该类可以实现元素的排序，添加的元素必须正确实现Comparable接口。
        map也是同样的，它也有SortedMap接口，TreeMap是实现类，该类实现按Key实现map的排序，添加的key必须实现Comparable接口。
         */
        String str1 = "Jonty";
        String str2 = "yang";
        System.out.println(str1.compareTo(str2));
    }
}
