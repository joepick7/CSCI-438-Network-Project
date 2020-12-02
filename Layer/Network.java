package Layer;
import NetworkGraph.*;

import java.net.InetAddress;
import java.util.ArrayList;

public class Network {
    private ArrayList<NetworkGraph> configurations = new ArrayList<>();
	private int configuration;

    public Network() {
        String[] fileNames = {"C:\\Users\\Public\\ITC-1.txt"};
		this.configuration = 0;


		for(String s: fileNames) {
			NetworkGraph network = new NetworkGraph(s);
			configurations.add(network);
			System.out.println("Added the following network configuration:");
			System.out.println(network.toString());
		}
	}
	public void setConfig(int config) {
		if(config>0 && config < configurations.size()){
			this.configuration = config;
		}	
	}
	
	public String findLocalHostName(){
		NetworkGraph network = configurations.get(configuration);
		NetworkNode node = network.findLocal();
		if(node == null) {
			return "NULL Node";
		}
		return node.getAddress().getHostName();
	}

	public InetAddress[] networkAddresses() {
		InetAddress[] addresses = new InetAddress[this.configurations.get(configuration).size()];
		ArrayList<NetworkNode> nodes = this.configurations.get(configuration).getNodes();
		for(int i = 0; i < this.configurations.get(configuration).size(); i++) {
			addresses[i] = nodes.get(i).getAddress();
		}
		return addresses;
	}
	public static InetAddress[] testAddresses() {
		InetAddress[] addresses = new InetAddress[4];
		try{
		byte[] personByte = {((Integer)184).byteValue(),((Integer)16).byteValue(),((Integer)194).byteValue(),((Integer)75).byteValue()};
		InetAddress person = InetAddress.getByAddress(personByte);
		byte[] remoteHostByte =  {((Integer)45).byteValue(),((Integer)16).byteValue(),((Integer)206).byteValue(),((Integer)226).byteValue()};
		InetAddress remoteHost = InetAddress.getByAddress(remoteHostByte);
		addresses[0] = InetAddress.getByName("Joseph");
		addresses[1] = person;
		addresses[2] = InetAddress.getByName("DESKTOP-EKIC6SM");
		addresses[3] = remoteHost;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addresses;
	} 
}
