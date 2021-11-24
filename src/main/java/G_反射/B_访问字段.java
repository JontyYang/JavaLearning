package G_反射;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

/*
对任意的一个Object实例，只要我们获取了它的Class，就可以获取它的一切信息。

Class类提供了以下几个方法来获取字段：
Field getField(name)：根据字段名获取某个public的field（包括父类）
Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
Field[] getFields()：获取所有public的field（包括父类）
Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
 */
public class B_访问字段 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class stdClass = Member.class;
        //获取父类public字段groupName
        System.out.println(stdClass.getField("groupName"));
        //获取本类public字段memberName
        System.out.println(stdClass.getField("memberName"));
        //获取本类private字段score
        System.out.println(stdClass.getDeclaredField("score"));
        //获取本类和继承类的public字段
        System.out.println(Arrays.toString(stdClass.getFields()));
        //获取本类的所有字段
        System.out.println(Arrays.toString(stdClass.getDeclaredFields()));

        /*
        一个Field对象包含了一个字段的所有信息：
        getName()：返回字段名称，例如，"name"；
        getType()：返回字段类型，也是一个Class实例，例如，String.class；
        getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
         */
        Field field1 = stdClass.getDeclaredField("score");
        System.out.println(field1.getName());
        System.out.println(field1.getType());
        System.out.println(field1.getModifiers());
        int m = field1.getModifiers();
        System.out.println(Modifier.isFinal(m));
        System.out.println(Modifier.isPublic(m));
        System.out.println(Modifier.isProtected(m));
        System.out.println(Modifier.isPrivate(m));
        System.out.println(Modifier.isStatic(m));

        /**
         * 获取字段值
         * Field通过方法field.get(Object实例)获取指定实例的指定字段的值，
         * 如果获取的是private属性的值，应在语句之前加field.setAccessible(true)
         *
         * 通过反射读写字段是一种非常规方法，它会破坏对象的封装。
         * 有童鞋会问：如果使用反射可以获取private字段的值，那么类的封装还有什么意义？
         * 答案是正常情况下，我们总是通过p.name来访问Person的name字段，编译器会根据public、protected和private决定是否允许访问字段，这样就达到了数据封装的目的。
         * 而反射是一种非常规的用法，使用反射，首先代码非常繁琐，其次，它更多地是给工具或者底层框架来使用，目的是在不知道目标实例任何信息的情况下，获取特定字段的值。
         * 此外，setAccessible(true)可能会失败。如果JVM运行期存在SecurityManager，那么它会根据规则进行检查，有可能阻止setAccessible(true)。
         * 例如，某个SecurityManager]
         * 可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。
         *
         */
        Member member1 = new Member("Project1", "jonty", "99");
        field1.setAccessible(true);
        System.out.println(field1.get(member1));

        /*
        设置字段值
        通过Field实例既然可以获取到指定实例的字段值，自然也可以设置字段的值。
        设置字段值是通过Field.set(Object, Object)实现的，其中第一个Object参数是指定的实例，第二个Object参数是待修改的值
         */
        field1.setAccessible(true);
        field1.set(member1, "100");
        System.out.println(member1.toString());

    }
}

class Group {
    public String groupName;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(getGroupName(), group.getGroupName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                '}';
    }
}

class Member extends Group {
    public String memberName;
    private String score;

    public Member() {
    }

    public Member(String groupName, String memberName, String score) {
        super(groupName);
        this.memberName = memberName;
        this.score = score;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return Objects.equals(getMemberName(), member.getMemberName()) &&
                Objects.equals(getScore(), member.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMemberName(), getScore());
    }

    @Override
    public String toString() {
        return "Member{" +
                "groupName='" + groupName + '\'' +
                ", memberName='" + memberName + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}