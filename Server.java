import Layer.Stack;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 

public class Server implements Runnable{
	public void run() {
		try {
            DatagramSocket link = new DatagramSocket(5555);
            byte[] receiveBuffer = new byte[65535];
            DatagramPacket linkMessage = null;
            while(true) {
                linkMessage = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                link.receive(linkMessage);
                System.out.println(convertToString(receiveBuffer));
                receiveBuffer = new byte[65535];
            }
            }catch(Exception e) {
                e.printStackTrace();
            }
    }
    public static String convertToString(byte[] byteFrame){
        if(byteFrame == null) {
            return null;
        }
        String message = "";
        for(int i = 0; i < byteFrame.length; i++) {
            if(byteFrame[i] == 0){
                break;
            }
            message = message +  (char)byteFrame[i];
        }
        return message;
    }
}