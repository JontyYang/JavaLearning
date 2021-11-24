package A_Java快速入门.A_Java简介;

/*
* javac（java编译器）将java源码文件编译为.class文件
* java（JVM）将.class文件运行生成机器指令
* jar 将.class文件打为jar包
* javadoc 将源码文件中的注释提取出来，生成文档
* jdb java调试器
* */
// 一个java源码文件中，只能有一个public类，且该类名称与文件名一致(大写),目的是为了使每个编译单元只有一个公共接口
public class 第一个Java程序 {
    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}


