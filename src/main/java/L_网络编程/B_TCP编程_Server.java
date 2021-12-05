package L_网络编程;

/*
在开发网络应用程序的时候，会遇到Socket这个概念，Socket是一个抽象概念，一个应用程序通过一个Socket来建立一个远程连接，而Socket内部通过
TCP/IP协议把数据传输到网络：
┌───────────┐                                                ┌───────────┐
│     Application     │                                                │     Application    │
├───────────┤                                                ├───────────┤
│       Socket        │                                                │       Socket       │
├───────────┤                                                 ├──────────┤
│        TCP          │                                                │       TCP         │
├───────────┤      ┌──────┐       ┌──────┐      ├──────────┤
│        IP           │<──>│  Router    │<──>│    Router  │<──>│        IP         │
└───────────┘      └──────┘       └──────┘      └──────────┘
Socket、TCP和部分IP的功能都是由操作系统提供的，不同的编程语言只是提供了对操作系统调用的简单封装。例如，Java提供的几个Socket相关的类就封装
了操作系统提供的接口。
为什么需要Socket进行网络通信？因为通过IP进行通信是不够的，同一台计算机同一时间会运行多个网络应用程序。例如qq、浏览器、邮件等。当操作系统收到一个数据包时，
如果只有IP，他没法判断应发给那个应用程序。所以，操作系统抽象出Socket接口，每个应用程序需要各自对应到不同的Socket，数据包才能根据Socket正确发给应用程序。

一个Socket就是由IP地址和端口号（0~65535）组成，可以把Socket理解为IP+端口号（IP：端口号）。端口号总是由操作系统分配，是一个0-65535间的数字，小于1024的端口属于特权端口，
需要管理员权限，大于1024的端口可以有任意用户的应用程序打开。

使用Socket进行网络编程时，本质上就是两个进程之间的网络通信。其中一个进行充当服务器，主动监听某个指定端口，另一个进行必须充当客户端，主动连接服务器，如果连接成功，服务器和
客户端就建立了一个TCP连接，双方后续可以随时发送和接受数据。

因此，当Socket连接在服务器和客户端成功建立后：
    对服务器来说，它的Socket是指定的IP：端口号
    对客户端来说，它的Socket是他所在计算机IP地址和一个由操作系统分配的随机端口号。
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 要是用Socket编程，首先要编写服务器端程序，Java提供了ServerSocket来实现对指定IP和指定端口的监听，实现如下：
 */
public class B_TCP编程_Server {
    public static void main(String[] args) throws IOException {
        //监听指定端口，没有指定IP，表示在计算机所有网络监听。
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server is running...");
        for (; ; ) {
            /*
            使用一个无限循环来处理客户端的连接
            serverSocket.accept()方法表示每当有客户端连接时，就返回一个Socket实例，这个Socket实例就是用来和刚连接的客户端进行通行的。由于客户端很多，
            要实现并发，我们必须为每个Socket创建新线程来处理，这样，主线程作用就是接收新连接，每当收到新连接后，就创建一个新线程处理。

            我们在多线程编程章节中也介绍过线程池，这里可以完全利用线程池来处理客户端连接，能大大提高效率。

            如果没有客户端连接进来，accept方法会阻塞并一直等待。如果有多个客户端同时连接进来，ServerSocket会把连接扔到队列中，之后一一处理。对于java，
            只需要通过循环不断调用accept方法就可以获得新的连接。
             */
            Socket socket = serverSocket.accept();
            System.out.println("connected from " + socket.getRemoteSocketAddress());
            Thread thread = new B_Handler(socket);
            thread.start();
        }
    }
}

//把每个Socket连接当做线程
class B_Handler extends Thread {
    Socket sock;

    public B_Handler(Socket socket) {
        this.sock = socket;
    }

    @Override
    public void run() {
        //打开相对于Server的输入流和输出流
        try (InputStream inputStream = this.sock.getInputStream()) {
            try (OutputStream outputStream = this.sock.getOutputStream()) {
                //进行处理
                this.handle(inputStream, outputStream);
            }
        } catch (Exception e) {
            try {
//                当连接期间发生异常时，关闭socket
                this.sock.close();
            } catch (IOException ioe) {
                System.out.println("client disconnected.");
            }
        }
    }

    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException {
        var writer = new BufferedWriter((new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)));
        var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        writer.write("hello\n");
        //刷新缓存
        writer.flush();
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            String s = reader.readLine();
            System.out.println("<<<" + s);
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            System.out.println(">>>");
            String string = scanner.nextLine();
            writer.write(string + "\n");
            writer.flush();
        }
    }
}

/*
Socket流
当Socket连接创建成功后，无论是服务器端，还是客户端，我们都使用Socket实例进行网络通信。因为TCP是一种基于流的协议，
因此，Java标准库使用InputStream和OutputStream来封装Socket的数据流，这样我们使用Socket的流，和普通IO流类似：

// 用于读取网络数据:
InputStream in = sock.getInputStream();
// 用于写入网络数据:
OutputStream out = sock.getOutputStream();
最后我们重点来看看，为什么写入网络数据时，要调用flush()方法。

如果不调用flush()，我们很可能会发现，客户端和服务器都收不到数据，这并不是Java标准库的设计问题，而是我们以流的形式写入数据的时候，
并不是一写入就立刻发送到网络，而是先写入内存缓冲区，直到缓冲区满了以后，才会一次性真正发送到网络，这样设计的目的是为了提高传输效率。
如果缓冲区的数据很少，而我们又想强制把这些数据发送到网络，就必须调用flush()强制把缓冲区数据发送出去。


使用Java进行TCP编程时，需要使用Socket模型：

服务器端用ServerSocket监听指定端口；
客户端使用Socket(InetAddress, port)连接服务器；
服务器端用accept()接收连接并返回Socket；
双方通过Socket打开InputStream/OutputStream读写数据；
服务器端通常使用多线程同时处理多个客户端连接，利用线程池可大幅提升效率；
flush()用于强制输出缓冲区到网络。
 */