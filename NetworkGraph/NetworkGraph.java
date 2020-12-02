package NetworkGraph;

import java.net.InetAddress;
import java.util.ArrayList;

public class NetworkGraph {
	ArrayList<NetworkNode> nodes;
	ArrayList<NetworkEdge> edges;
	
	//Create a new empty NetworkGraph.
	public NetworkGraph() {
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}
	
	//Create a new NetworkGraph and then load it with the file selected. 
	public NetworkGraph(String fileName) {
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.loadFile(fileName);
	}
	
	public void load(String[] line) {
		/*
		 * This section deals with adding the nodes from a parsed and verified line from an irc file. 
		 */
		
		//Check if focus node is in the graph, if not add in a full node.
		int hostID  = Integer.parseInt(line[0]);
		NetworkNode focusNode = null;
		if(!(this.containsNodeId(hostID))) {
			NetworkNode newNode = null; 
			if(line.length == 6){
				newNode = new NetworkNode(hostID, line[1], Integer.parseInt(line[2]), line[1]);
			}
			//Check line containing potential  connection scope tag.
			if(line.length == 7) {
				
				newNode = new NetworkNode(hostID, line[1], Integer.parseInt(line[2]), NetworkParser.convertToIP(line[1]));
			}
			this.addNode(newNode);
			focusNode = newNode;
		}
		//If node is in the graph but is not in a defined state, define the node.
		else {
			focusNode = getNode(hostID);
			if(!focusNode.isDefined()) {
				if(line.length == 6){
					focusNode.defineNode(line[1], Integer.parseInt(line[2]), line[1]);
				}
				if(line.length == 7) {
						focusNode.defineNode(line[1], Integer.parseInt(line[2]), NetworkParser.convertToIP(line[1]));
				}
			}
		}
		
		//Check if the first destination machine is in the graph, if not add a placeholder node.
		int destination1 = Integer.parseInt(line[3]);
		if(!(this.containsNodeId(destination1)) && destination1 != 0) {
			NetworkNode newNode = new NetworkNode(destination1);
			this.addNode(newNode);
		}
		
		//Check if the second destination machine is in the graph, if not add a placeholder node/  
		int destination2 = Integer.parseInt(line[4]);
		if(!(this.containsNodeId(destination2)) && destination2 != 0) {
			NetworkNode newNode = new NetworkNode(destination2);
			this.addNode(newNode);
		}
		
		
		/*
		 * This section deals with adding defined edges from a parsed and verified line from an irc file. 
		 */
		int transmissionLimit = Integer.parseInt(line[5]);
		
		if(destination1 != 0) {
			NetworkEdge edge = new NetworkEdge(hostID, destination1, transmissionLimit);
			edges.add(edge);
		}
		if(destination2 != 0) {
			NetworkEdge edge = new NetworkEdge(hostID, destination2, transmissionLimit);
			edges.add(edge);
		}
		
		
	}
	public void loadFile(String fileName) {
		ArrayList<String[]> file = NetworkParser.parse(fileName);
		
		for(String[] s: file) {
			this.load(s);
		}
		
		
	}
	public void addNode(NetworkNode node) {
		this.nodes.add(node);
	}
	private boolean containsNodeId(int id) {
		for(NetworkNode node: nodes) {
			if(node.getNodeID() == id) {
				return true;
			}
		}
		return false;
	}
	//Invoked only when it is known that the node id exists in the graph
	private NetworkNode getNode(int id) {
		NetworkNode node = null;
		for(NetworkNode n: nodes) {
			if(n.getNodeID() == id) {
				node = n;
			}
		}
		return node; 
	}
	
	
	public String toString() {
		String output = "";
		
		for(NetworkNode n: this.nodes) {
			output += n.toString() + "\n";
		}
		
		for(NetworkEdge e: this.edges) {
			output += e.toString() + "\n";
		}
		
		return output;
	}
	//Return NetworkNode with specified id.
	public NetworkNode findLocal(){
		try{
		InetAddress local = InetAddress.getLocalHost();
		for(NetworkNode nn : this.nodes) {

			if(nn.equalsAddress(local)) {
				return nn;
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public int size() {
		return this.nodes.size();
	}
	public ArrayList<NetworkNode> getNodes() {
		return this.nodes;
	}
}
