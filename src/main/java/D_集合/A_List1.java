package D_集合;

import java.util.*;

/**
 * Java的集合类定义在java.util包中，支持泛型，主要提供了3种集合类，包括List，Set和Map
 *
 * Java标准库自带的java.util包提供了集合类：Collection，它是除Map外所有其他集合类的根接口
 *
 * List：一种有序列表的集合(顺序排列有序，存储无序)，例如，按索引排列的Student的List； 具体的实现类有ArrayList，LinkedList等
 * Set：一种保证没有重复元素的集合，例如，所有无重复名称的Student的Set
 * Map：一种通过键值（key-value）查找的映射表集合，例如，根据Student的name查找对应Student的Map
 *
 * Java访问集合总是通过统一的方式——迭代器（Iterator）来实现，它最明显的好处在于无需知道集合内部元素是按什么方式存储的
 * 对于数组而言，元素的增删实现麻烦。
 */
public class A_List1 {
    public static void main(String[] args) {
        //ArrayList
        ArrayList<Character> arrayList = new ArrayList<Character>(5);
        //指定大小为5，size为0（size为元素个数）
        System.out.println(arrayList.size());
        /*
        在实际应用中，我们使用ArrayList有序列表增删元素
        1.ArrayList实际上内部使用了数组来存储元素，相当于一个封装了删除和添加元素方法的数组。
        2.例如一个ArrayList拥有5个元素，size（）=5，但是内部分配存储时末尾分配一个空位（所以实际数组大小是size+1=6）
        3.当添加或者删除一个元素时ArrayList会自动移动需要移动的元素，之后在指定索引位置添加或者删除元素，size加一或者减一（为6或者4）
        4.当size为6时，ArrayList已满，继续添加元素，这时没有空闲位置，ArrayList会创建一个更大的数组，将旧数组元素复制，使用新数组进行替代。
         */
        arrayList.add('A');
        System.out.println(arrayList.size());
        /*
        ArrayList把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动
        在末尾添加一个元素：boolean add(E_Writer e)
        在指定索引添加一个元素：boolean add(int index, E_Writer e)
        删除指定索引的元素：E_Writer remove(int index)
        删除某个元素：boolean remove(Object e)
        获取指定索引的元素：E_Writer get(int index)
        获取某个元素索引： indexof，lastIndexof
        获取链表大小（包含元素的个数）：int size()
         */


        //LinkedList(链表）
        /**
         * 	                   ArrayList	    LinkedList
         * 获取指定元素	        速度很快	        需要从头开始查找元素
         * 添加元素到末尾	    速度很快	        速度很快
         * 在指定位置添加/删除	需要移动元素 	不需要移动元素
         * 内存占用	            少	            较大
         */
        //通常情况下，总是优先使用ArrayList

        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("apple");
        arrayList1.add("apple");
        arrayList1.add("banana");
        //允许添加null值
        arrayList1.add(null);
        //遍历ArrayList,顺序从0开始
        //索引遍历
        for (int i = 0; i < arrayList1.size(); i++) {
            String string = arrayList1.get(i);
            System.out.println(string);
        }
        /*
        索引遍历这种方式并不推荐LinkedList使用，因为索引越大其访问速度越慢。
        所以我们要坚持使用迭代器Iterator来访问List,总是具有最高的访问效率。
        for each循环默认是使用iterator方法进行遍历的，这是因为编译器将for each变为Iteratot的调用。
         */
        for (Iterator<String> iterator = arrayList1.iterator(); iterator.hasNext();) {
            String string = iterator.next();
            System.out.println(string);
        }
        //元素遍历
        for (String str : arrayList1) {
            System.out.println(str);
        }
        //元素遍历
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("jjj");
        for (String str1 : linkedList) {
            System.out.println(str1);
        }
        linkedList.iterator();

        //List和Array转换
        //toArray方法中传入一个类型相同的数组，List内部把元素复制到该数组中；
        //如果传入的数组过大，则剩余值为如String类型为null；如果过小，list内部会创建一个新的刚好够大的数组，填充后返回。
        String[] strings = arrayList1.toArray(new String[arrayList1.size()]);
        System.out.println(Arrays.toString(strings));

        //使用数组方法转换
        Integer[] int1 = {1, 2, 3};
        List<Integer> list = Arrays.asList(int1);
        for (Integer n : list) {
            System.out.println(n);
        }
        //使用List.of方法，返回一个只读List
        List<Integer> list11= List.of(int1);

    }
}
