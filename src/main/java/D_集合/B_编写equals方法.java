package D_集合;

import java.util.*;

public class B_编写equals方法 {
    public static void main(String[] args) {
        Integer[] integer = {1, 2, 3};
        List<Integer> list = new ArrayList<Integer>();
        list.add(Integer.valueOf(1));

        //使用contains(object O)方法判断列表中是否包含该元素
        System.out.println(list.contains(1));
        //使用indexOf(Object o)方法返回某个元素的索引
        //List内部使用equals方法判断量元素内容是否相同
        int order = list.indexOf(1);
        System.out.println(order);

        /**
         * 如果放入的元素需要使用List的contains，indexOf，remove方法（参数为元素），那么放入的元素就需要实现equals方法
         */
        Person[] person = {
               new Person("jonty", 1)
        };
        List<Person> personList = Arrays.asList(person);
        System.out.println(personList.contains(new Person("jonty", 2)));
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}