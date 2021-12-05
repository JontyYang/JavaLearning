package L_网络编程;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class B_TCP编程_Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6666);
        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                handle(inputStream, outputStream);
            }
        }
        socket.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        var reader = new BufferedReader((new InputStreamReader(inputStream, StandardCharsets.UTF_8)));
        //读取控制台
        Scanner scanner = new Scanner(System.in);
//        读取服务器
        System.out.println("[Server] " + reader.readLine());
        for (; ; ) {
            System.out.println(">>>");   //打印提示
            String string = scanner.nextLine();
            writer.write(string);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            //如果发送了bye，server会停止，server也会发送bye给Client使其结束进程。
            if (resp.equals("bye")) {
                break;
            }
        }
    }

}
