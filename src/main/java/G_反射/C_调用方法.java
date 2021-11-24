package G_反射;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 可以通过Class实例获取所有Method信息。Class类提供了以下几个方法来获取Method：
 *
 * Method getMethod(name, Class...)：获取某个public的Method（包括父类）
 * Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
 * Method[] getMethods()：获取所有public的Method（包括父类）
 * Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
 *
 * 一个Method对象包含一个方法的所有信息：
 * getName()：返回方法名称，例如："getScore"；
 * getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
 * getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
 * getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
 *
 */
public class C_调用方法 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /**
         *调用方法
         * 对Method实例调用invoke就相当于调用该方法，invoke的第一个参数是对象实例，即在哪个实例上调用该方法，后面的可变参数要与方法参数一致，否则将报错。
         */
        // String对象:
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method m = String.class.getMethod("substring", int.class);
        // 在s对象上调用该方法并获取结果:
        String r = (String) m.invoke(s, 6);
        // 打印调用结果:
        System.out.println(r);

        /*
        如果获取到的Method表示一个静态方法，调用静态方法时，由于无需指定实例对象，所以invoke方法传入的第一个参数永远为null
         */
        // 获取Integer.parseInt(String)方法，参数为String:
        Method m1 = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果:
        Integer n = (Integer) m1.invoke(null, "12345");
        // 打印调用结果:
        System.out.println(n);

        /*
        调用非public方法
        和Field类似，对于非public方法，我们虽然可以通过Class.getDeclaredMethod()获取该方法实例，
        但直接对其调用将得到一个IllegalAccessException。为了调用非public方法，我们通过Method.setAccessible(true)允许其调用：
         */

        /**
         * Java的反射API提供的Method对象封装了方法的所有信息：
         *
         * 通过Class实例的方法可以获取Method实例：getMethod()，getMethods()，getDeclaredMethod()，getDeclaredMethods()；
         *
         * 通过Method实例可以获取方法信息：getName()，getReturnType()，getParameterTypes()，getModifiers()；
         *
         * 通过Method实例可以调用某个对象的方法：Object invoke(Object instance, Object... parameters)；
         *
         * 通过设置setAccessible(true)来访问非public方法；
         *
         * 通过反射调用方法时，仍然遵循多态原则。
         */

    }
}
