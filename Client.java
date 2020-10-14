import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Scanner; 

public class Client implements Runnable{
	public void run(){
		try{
		System.out.println("Client initializing...");
		DatagramSocket link = new DatagramSocket();
		Scanner input = new Scanner(System.in);
		InetAddress ip = InetAddress.getLocalHost();
		byte buffer[] = null; 
		System.out.println("Client initialized");
		while(true) {
			String message = input.nextLine();
			buffer = message.getBytes();
			//If user inputs exit, undergo exit procedures and break loop, thread then terminates. 
			if(message.equalsIgnoreCase("exit")) {
				buffer = (new String("Logging off")).getBytes();
				DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ip,1234);
				link.send(segment);
				break;
			}
			
			DatagramPacket segment = new DatagramPacket(buffer, buffer.length, ip, 1234);
			link.send(segment);
		}
		link.close();
		input.close();
		}catch(Exception e) {
			System.out.println("Error in Client!"); 
		}
	}
}