package NetworkGraph;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkNode {
	private String hostName;
	private Integer nodeID;
	private Integer udpPort;
	private InetAddress info;
	
	public NetworkNode(int nodeID, String hostName, int udpPort, String Connection) {
		this.hostName = hostName;
		this.nodeID = nodeID;
		this.udpPort = udpPort;
		try{
			this.info = InetAddress.getByName(Connection);
			} catch(UnknownHostException e) {
				System.out.println("Unknown host found when attempting to process connection");
			}
	}
	public NetworkNode(int nodeID, String hostName, int udpPort, byte[] Connection) {
		this.hostName = hostName;
		this.nodeID = nodeID;
		this.udpPort = udpPort;
		try{
		this.info = InetAddress.getByAddress(Connection);
		} catch(UnknownHostException e) {
			System.out.println("Unknown host found when attempting to process connection");
		}
	}
	public NetworkNode(int nodeID) {
		this.nodeID = nodeID;
		this.hostName = null;
		this.udpPort = null;
		this.info = null;
	}
	public int getUdpPort() {
		return this.udpPort;
	}
	public int getNodeID() {
		return this.nodeID;
	}
	public void defineNode(String hostName, int udpPort, String connect) {
		this.hostName = hostName;
		this.udpPort = udpPort;
		try{
			this.info = InetAddress.getByName(connect);
			} catch(UnknownHostException e) {
				System.out.println("Unknown host found when attempting to process connection");
			}
		
	}
	public void defineNode(String hostName, int udpPort, byte[] connect) {
		this.hostName = hostName;
		this.udpPort = udpPort;
		try{
			this.info = InetAddress.getByAddress(connect);
			} catch(UnknownHostException e) {
				System.out.println("Unknown host found when attempting to process connection");
			}
		
	}
	public boolean isDefined() {
		return !(this.hostName == null && this.udpPort == null && this.info == null);
	}
	public String toString() {
		String nodeString = String.format("Node ID: %d  Hostname: %s  UDP Port: %d" , this.nodeID, this.hostName,this.udpPort);
		return nodeString;
	}

	public InetAddress getAddress()
	{
		return this.info;
	}

	public boolean equalsAddress(InetAddress address){
		if(address.getHostName().equals(info.getHostName())){
			return true;
		}
		return false;
	}
	
}
