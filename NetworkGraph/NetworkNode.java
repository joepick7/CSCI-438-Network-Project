package NetworkGraph;

public class NetworkNode {
	private String hostName;
	private Integer nodeID;
	private Integer udpPort;
	
	public NetworkNode(int nodeID, String hostName, int udpPort) {
		this.hostName = hostName;
		this.nodeID = nodeID;
		this.udpPort = udpPort;
	}
	public NetworkNode(int nodeID) {
		this.nodeID = nodeID;
		this.hostName = null;
		this.udpPort = null;
	}
	public int getUdpPort() {
		return this.udpPort;
	}
	public int getNodeID() {
		return this.nodeID;
	}
	public void defineNode(String hostName, int udpPort) {
		this.hostName = hostName;
		this.udpPort = udpPort;
	}
	public boolean isDefined() {
		return !(this.hostName == null && this.udpPort == null);
	}
	public String toString() {
		String nodeString = String.format("Node ID: %d  Hostname: %s  UDP Port: %d" , this.nodeID, this.hostName,this.udpPort);
		return nodeString;
	}
	
}
