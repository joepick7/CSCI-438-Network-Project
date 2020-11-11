package NetworkGraph;

import java.util.ArrayList;

public class NetworkEdge{
	private Integer hostID1;
	private Integer hostID2;
	private int transmissionLimit;
	
	public NetworkEdge(Integer host1, Integer host2,int transmissionLimit) {
		this.hostID1 = host1;
		this.hostID2 = host2;
		this.transmissionLimit = transmissionLimit;
	}
	public boolean isAlive() {
		return this.transmissionLimit != 0;
	}
	public void setToDead() {
		this.transmissionLimit = 0;
	}
	public String toString() {
		String isAlive = String.format("The connection between nodes %d and %d can currently transmit a maximum of %d bytes per packet.", this.hostID1, this.hostID2, this.transmissionLimit);
		String isDead = String.format("The connection between nodes %d and %d is currently dead.", this.hostID1, this.hostID2);
		return (this.isAlive()) ? isAlive:isDead;
	}
	public ArrayList<Integer> getHosts() {
		ArrayList<Integer> hosts = new ArrayList<Integer>(2);
		hosts.add(hostID1);
		hosts.add(hostID2);
		return hosts;
	}
	public int getTransmissionRate() {
		return this.transmissionLimit; 
	}
	public void setTransmissionRate(int newRate) {
		this.transmissionLimit = newRate;
	}
	public Boolean equals(NetworkEdge ne) {
		
		if(ne.getHosts().contains(this.hostID1) && ne.getHosts().contains(this.hostID2) && ne.getTransmissionRate() == this.transmissionLimit) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
}
