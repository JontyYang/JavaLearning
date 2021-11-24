package C_泛型;

import java.util.Arrays;
import java.util.Objects;

//除了类可以使用泛型外，接口也可以使用泛型，
//Arrays.sort(Object[])可以对任意数组进行排序，
//但待排序的元素必须实现Comparable<T>这个泛型接口
public class B_泛型接口 {
    public static void main(String[] args) {
        //因为String类型已实现Comparable<String>F_接口，所以默认可以调用该方法
        TemClass[] temClass = new TemClass[] {
            new TemClass("Bob", 12),
            new TemClass("Dob", 12),
            new TemClass("Cob", 12)
        };
        Arrays.sort(temClass);
        System.out.println(Arrays.toString(temClass));
        System.out.println(new TemClass("Bob", 12).hashCode());
        new TemClass("Bob", 12).printInfo();

        //实例泛型和静态方法的泛型是不同的，实例或者类指定泛型后不能调用静态泛型方法，
        //只能用类名Tem去调用静态方法泛型
        Tem<String> term = new Tem<String>("name");
        System.out.println(Tem.get("name").getClass());
    }
}

class TemClass implements Comparable<TemClass> {
    String name;
    int age;

    public TemClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TemClass() {
    }

    public static void printInfo() {
        System.out.println("我是静态方法");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemClass temClass = (TemClass) o;
        return age == temClass.age &&
                Objects.equals(name, temClass.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "TemClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(TemClass o) {
        return this.name.compareTo(o.name);
    }
}

class Tem<T> {
    private T name;

    public Tem() {
    }

    public Tem(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    //静态方法的泛型类型和实例类型的泛型类型是区分开的
    public static <K> Tem<K> get(K name) {
        return new Tem<K>(name);
    }

    public static void printInfo() {
        System.out.println("我是静态方法");
    }
}

