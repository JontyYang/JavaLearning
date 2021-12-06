package D_集合;

import java.util.HashMap;
import java.util.Map;

/**
 * Map和List不同的是，Map存储的是key-value的映射关系，并且，
 * 它不保证顺序。在遍历的时候，遍历的顺序既不一定是put()时放入的key的顺序，
 * 也不一定是key的排序顺序。使用Map时，任何依赖顺序的逻辑都是不可靠的。
 *
 * map是一种键值映射表，通过key可以快速找到value
 * HashMap之所以能根据key直接拿到value，原因是它内部通过空间换时间的方法，
 * 用一个大数组存储所有value，并根据key直接计算出value应该存储在哪个索引（和中文字典差不多)
 *
 * 通过key计算索引的方式就是调用key对象的hashCode()方法，它返回一个int整数。
 * HashMap正是通过这个方法直接定位key对应的value的索引，继而直接返回value。
 */
public class C_使用Map {
    public static void main(String[] args) {
        /**
         * Map这种键值（映射表）的数据结构，作用就是能高效通过key快速查找value
         */
        Student student = new Student("Jonty", 100);
        System.out.println(student);
        Map<String, Student> map = new HashMap<String, Student>();
        //添加元素
        map.put("Jonty", student);
        Student stu = map.get("Jonty");
        System.out.println(stu == student);
        //删除元素
        map.remove("Jonty");
        /*
        使用containsKey，containsValue查找key或者value是否存在。
        map中不能有重复的key
         */

        //通过key遍历map
        map.put("jonty", student);
        map.put("jon", student);
        for (String key : map.keySet()) {
            Student student1 = map.get(key);
            System.out.println(key + "=" + student1.Score);
        }
        //通过entry遍历key和value
        for (Map.Entry<String, Student> entry : map.entrySet()) {
            String key = entry.getKey();
            Student student2 = entry.getValue();
            System.out.println(key + "=" + student2.Score);
        }

        //通过value遍历
        for (Student stude : map.values()) {
            System.out.println(stude.Score);
        }

        /*
        在Map的内部，对key做比较是通过equals()实现的，这一点和List查找元素需要正确覆写equals()是一样的，
        即正确使用Map必须保证：作为key的对象必须正确覆写equals()方法。
        我们经常使用String作为key，因为String已经正确覆写了equals()方法。
        但如果我们放入的key是一个自己写的类，就必须保证正确覆写了equals()方法。
         */

        //如上例，hashmap通过key对象的hashCode()方法返回一个整数，该整数则为value的索引。
        String str = "jjj";
        System.out.println(str.hashCode());


    }
}

 class Student {
    private String name;
    public int Score;

     public Student() {
     }

     public Student(String name, int score) {
         this.name = name;
         Score = score;
     }
 }
