package I_正则表达式;

import java.util.HashMap;
import java.util.Map;

public class F_模板引擎 {
    public static void main(String[] args) {
   //   System.out.printf("Hello, %s! You are learning %s", "Jonty", "java");
        String string1 = "Hello, ${name}! You are learning ${language}!";
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "Bob");
        hashMap.put("name1", "Kill");
        hashMap.put("language", "Java");
        System.out.println(template(string1, hashMap));
    }

    //通过key的循环来进行替换
    static String template(String string, Map<String, String> map) {
        String data = string;
        for (String key : map.keySet()) {
            System.out.println(key);
            data = data.replaceAll("\\$\\{"+key+"}", map.get(key));
        }
        return data;
    }
}
