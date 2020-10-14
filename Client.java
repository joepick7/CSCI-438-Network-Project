import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Scanner; 

public class Client implements Runnable{
	
	
	public void run(){
		byte[] address = new byte[4];
		
		//192.168.254.33, Caleb Machine
		address[0] = ((Integer)184).byteValue();
		address[1] = ((Integer)16).byteValue();
		address[2] = ((Integer)207).byteValue();
		address[3] = ((Integer)89).byteValue();

		String remoteServerAddress = "lazarus.rjfortyfive.com";
		
		try{
		System.out.println("Client initializing...");
		String userName = "Joseph Pickard";
		DatagramSocket link = new DatagramSocket();
		Scanner input = new Scanner(System.in);
		InetAddress[] ips = {InetAddress.getByAddress(address), InetAddress.getByName(remoteServerAddress)};
		byte buffer[] = null; 
		System.out.println("Client initialized");
		for(int i = 0; i <  ips.length; i++) {
			buffer = (userName + " has come online").getBytes();
			DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ips[i],5555);
			link.send(segment);
		}
		
		
		while(true) {
			String message = input.nextLine();
			
			//If user inputs exit, undergo exit procedures and break loop, thread then terminates. 
			if(message.equalsIgnoreCase("exit")) {
				buffer = (new String(userName + " is logging off")).getBytes();
				for(int i = 0; i <  ips.length; i++) {
					DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ips[i],5555);
					link.send(segment);
				}
				System.out.println("Getting offline");
				break;
			}
			
			buffer = (userName + " : " + message).getBytes();	
			for(int i = 0; i <  ips.length; i++) {
				DatagramPacket segment = new DatagramPacket(buffer,buffer.length,ips[i],5555);
				link.send(segment);
			}
		}
		link.close();
		input.close();
		}catch(Exception e) {
			System.out.println("Error in Client!"); 
		}
	}
}