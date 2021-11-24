package D_集合;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

//Java默认配置文件是以.peoperties为扩展名，#开头的为注释，每行以key=value表示
//该配置文件的特点是，它的key-value一般都是String-String，因此可以使用Map<String, String>来表示
//因为配置文件常用，所以Java提供一个Properties来表示配置

//Input（OutputStream）Stream默认以ASCALL编码读取字节流，因此中文会有乱码
//Reader以字符流读取文件
public class D_使用Properties {
    public static void main(String[] args) {
        String str = "D:\\SS\\Idea\\workplace\\JavaLearning\\src\\Z_Common\\setting.properties";
        //创建Properties对象
        Properties props = new Properties();
        try {
            //创建文件输入流，加载
            props.load(new FileInputStream(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = props.getProperty("last_open_file");
        String auto_save_interval = props.getProperty("auto_save_interval");
        System.out.println(filePath + '\n' + auto_save_interval);
        //如果没有该key则返回指定值
        String auto_save_interval1 = props.getProperty("auto_save_interval1", "-1");
        System.out.println(auto_save_interval1);

        //写入文件
        props.setProperty("url", "www.baiDu.com");
        try {
            props.store(new FileOutputStream(str), "这是写入的注释");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过Reader读取字符流，中文不会乱码
        try {
            props.load(new FileReader(str, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
