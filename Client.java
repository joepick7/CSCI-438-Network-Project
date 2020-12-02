import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Scanner;

import Layer.Stack; 

public class Client implements Runnable{	
	
	public void run(){
		byte[] address = new byte[4];
		String username = "";
		try{
		username = InetAddress.getLocalHost().getHostAddress();			
		} catch(Exception e) {
			e.printStackTrace();
		}
		//192.168.254.33, Caleb Machine
		address[0] = ((Integer)184).byteValue();
		address[1] = ((Integer)16).byteValue();
		address[2] = ((Integer)207).byteValue();
		address[3] = ((Integer)89).byteValue();
		
		Stack networkStack = new Stack(true);

		try{
		System.out.println("Client initializing...");
		DatagramSocket link = new DatagramSocket();
		Scanner input = new Scanner(System.in);
		InetAddress[] ips = networkStack.getIps();
		byte buffer[] = null; 
		System.out.println("Client initialized");
		for(InetAddress ip: ips){
			try{
			String message = username + "has come online";
			buffer = message.getBytes();
			DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ip,5555);
			link.send(segment);}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		while(true) {
			String message = input.nextLine();
			
			//If user inputs exit, undergo exit procedures and break loop, thread then terminates. 
			if(message.equalsIgnoreCase("exit")) {
				buffer = (new String(username+ " is logging off")).getBytes();
				for(int i = 0; i <  ips.length; i++) {
					try{
					DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ips[i],5555);
					link.send(segment);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("Getting offline");
				break;
			}
			//Later implement with username prefix
			buffer = (username + " : " + message).getBytes();	
			for(int i = 0; i <  ips.length; i++) {
				try{
				DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ips[i],5555);
				link.send(segment);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		link.close();
		input.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in Client!"); 
		}
	}
}