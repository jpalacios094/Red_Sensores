package com.example.vikin.red_sensores;

import android.support.v4.app.FragmentPagerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class Server extends Thread{
	MainActivity activity;
	int cont=0;
	//TempFragment fragment;
	ServerSocket serverSocket;
	int envia = 0;
	String message = "";
	String recibe = "";
	static final int socketServerPORT = 8080;

	public Server(MainActivity activity) {
		this.activity = activity;
		//this.fragment = fragment;
		Thread socketServerThread = new Thread(new SocketServerThread());
		socketServerThread.start();
	}
	/*public Server(TempFragment tempFragment){
		this.fragment = tempFragment;
		Thread socketServerThread = new Thread(new SocketServerThread());
		socketServerThread.start();

	}*/

	public int getPort() {
		return socketServerPORT;
	}

	public void onDestroy() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public class SocketServerThread extends Thread {

		int count = 0;

		BufferedReader b;
		ServerSocket serverSocket;

		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(socketServerPORT);

				while (true) {
					//Escuchar clientes entrantes
					Socket socket = serverSocket.accept();
					//Este hilo escuchara los mensajes de los clientes
					Thread recibeMensajes = new Thread(new ClientMessages(socket,activity));
					recibeMensajes.start();
					//PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					/*BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					recibe = in.readLine() + "\n";
					count++;
					message = recibe + "\n";
					/*message += "#" + count + " from "
							+ socket.getInetAddress() + ":"
				    		+ socket.getPort() + "\n"
                            + recibe + "\n" ;

					activity.runOnUiThread(new Runnable() {

						@Override
						public void run() {
                            //activity.recibido.setText(recibe);
							activity.getMyData(message);

							//fragment.getMyData(message);
							//return message;
						}
					});

					//SocketServerReplyThread socketServerReplyThread = new SocketServerReplyThread(
					//		socket, count);
					//socketServerReplyThread.run();*/

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/*private class SocketServerReplyThread extends Thread {

		private Socket hostThreadSocket;
		int cnt;

		SocketServerReplyThread(Socket socket, int c) {
			hostThreadSocket = socket;
			cnt = c;
		}

		@Override
		public void run() {
			OutputStream outputStream;
			String msgReply = "Hello from Server, you are #" + cnt;

			try {
				outputStream = hostThreadSocket.getOutputStream();
				PrintStream printStream = new PrintStream(outputStream);
				printStream.print(msgReply);
				printStream.close();

				message += "replayed: " + msgReply + "\n";

				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
					    envia=1;
                        activity.getMyData(message,envia);
                        envia=0;
                        //fragment.getMyData(message);
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message += "Something wrong! " + e.toString() + "\n";
			}

			activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
				    envia=1;
                    activity.getMyData(message,envia);
                    envia=0;
                    //fragment.getMyData(message);
				}
			});
		}

	}*/

	public String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces
						.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress
							.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "Server running at : "
								+ inetAddress.getHostAddress();
					}
				}
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}
		return ip;
	}
}
