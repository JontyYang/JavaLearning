package G_反射;

/**
 * 通过Class实例获取class信息的方法称为反射（Reflection）；
 * JVM总是动态加载class，可以在运行期根据条件来控制加载class。
 *
 * 当编译器生成class文件后，JVM后动态加载class文件，每加载一种class文件,JVM就会为其创建一个Class类型的实例，
 * 并关联起来（以String为例，当JVM加载String类时，他首先读取String.class文件到内存，之后为其创建一个Class实例关联
 * Class cls = new Class(String)）
 * 这个Class实例是JVM创建的，因此其构造方法是private，只有JVM能创建该实例，自己是不能创建的，因此JVM创建的实例
 * 都指向一个数据类型（class或者interface）
 * public final class Class {
 *     private Class() {}
 * }
 * 由于JVM为每个加载的class创建了对应的Class实例，并在实例中保存了该class的所有信息，包括类名、包名、父类、实现的接口、所有方法、字段等，
 * 因此，如果获取了某个Class实例，我们就可以通过这个Class实例获取到该实例对应的class的所有信息。
 */
public class A_Class类 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*
        通过Class实例获取class信息的方法称为反射（Reflection）,有三种方式,
        因为JVM创建的Class实例是唯一的，所以上述方法获取的Class实例是同一个实例，可以用==进行比较
         */
        //方法一：直接通过一个class的静态变量class获取
        Class cls1 = String.class;
        //方法二：如果有实例变量，可以通过该实例变量提供的getClass方法获取
        String str = "HEllo";
        Class cls2 = str.getClass();
        //方法三：如果知道一个类的完整类名，可以通过静态方法Class.forName()获取
        Class cls3 = Class.forName("java.lang.String");
        /*
        通常情况下，我们应该用instanceof判断数据类型，因为面向抽象编程的时候，我们不关心具体的子类型。
        只有在需要精确判断一个类型是不是某个class的时候，我们才使用==判断class实例
         */
        //注意到数组(例如String[])也是一种Class，而且不同于String.class，它的类名是【Ljava.lang.String，此外，
        //JVM也为每一种基本类型创建了Class,通过int.class访问
        int[] ints = {};
        System.out.println(ints.getClass());
        String[] strs = {"1", "2"};
        System.out.println(strs.getClass());
        System.out.println(int.class);

        //如果获取到一个类的Class实例，我们可以通过Class实例来创建对应类型的实例，
        Class cls4 = Integer.class;
        Integer integer = (Integer) cls4.newInstance();
        /*
        上述代码相当于new Integer()。通过Class.newInstance()可以创建类实例，但是它的局限是：
        只能调用public的无参数构造方法。带参数的构造方法，或者非public的构造方法都无法通过Class.newInstance()被调用。
         */

        /*
        动态加载
        JVM在执行Java程序的时候，并不是一次性把所有用到的class全部加载到内存，而是第一次需要用到class时才加载。
        动态加载class的特性对于Java程序非常重要。利用JVM动态加载class的特性，我们才能在运行期根据条件加载不同的实现类。
        例如，Commons Logging总是优先使用Log4j，只有当Log4j不存在时，才使用JDK的logging。
         */

    }
}
