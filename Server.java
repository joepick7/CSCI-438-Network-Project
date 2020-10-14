import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
public class Server implements Runnable{
	public void run() {
		try {
		DatagramSocket link = new DatagramSocket(1234);
		byte[] receiveBuffer = new byte[65535];
		DatagramPacket linkMessage = null;
		while(true) {
			linkMessage = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			link.receive(linkMessage);
			System.out.println("Message received: " + convertToString(receiveBuffer));
			receiveBuffer = new byte[65535];
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String convertToString(byte[] byteMessage){
		if(byteMessage == null) {
			return null;
		}
		String message = "";
		for(int i = 0; i < byteMessage.length; i++) {
			if(byteMessage[i] == 0){
				break;
			}
			message = message +  (char)byteMessage[i];
		}
		return message;
	}
}