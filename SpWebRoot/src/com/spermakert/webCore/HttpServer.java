package com.spermakert.webCore;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	/**
	 * @param args
	 */
	
	//WEB_ROOT是改服务器的根目录
	public static final String WEB_ROOT = System.getProperty("user.dir")+File.separator+"webroot";
	
	//关闭的命令
	private static final String SHUTDOWN_COMMAND= "/SHUTDOWN";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpServer server = new HttpServer();
		server.await();

	}
	
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
			while (true) {
				try {
					Socket socket = null;
					InputStream input = null;
					OutputStream output = null;
					socket = serverSocket.accept();
					input = socket.getInputStream();
					output = socket.getOutputStream();
					// 封装request请求
					Request request = new Request(input);
					request.parse();
					// 封装response对象
					Response response = new Response(output);
					response.setRequest(request);
					response.sendStaticResource();
					socket.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
