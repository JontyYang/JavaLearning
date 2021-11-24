package B_面向对象编程.B_Java核心类;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * JavaBean是一种符合命名规范的class，它通过getter和setter来定义属性；
 *
 * 属性是一种通用的叫法，并非Java语法规定；
 *
 * 可以利用IDE快速生成getter和setter；
 *
 * 使用Introspector.getBeanInfo()可以获取属性列表
 */
public class E_JaBean {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChild() {
        return age <= 6;
    }

    public static void main(String[] args) throws Exception{
        BeanInfo beanInfo = Introspector.getBeanInfo(E_JaBean.class);
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("   " + pd.getReadMethod());
            System.out.println("   " + pd.getWriteMethod());
        }
    }
}
