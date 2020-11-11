package NetworkGraph;

import java.util.ArrayList;

public class NetworkTester {

	public static void main(String[] args) {
		/*
		 * Array contains absolute path to each file on the machine, please 
		 * update for your particular machine. 
		 */
		String[] fileNames = {"C:\\Users\\jcp97_000\\Downloads\\ITC-1.txt",
				"C:\\Users\\jcp97_000\\Downloads\\ITC-2.txt",
				"C:\\Users\\jcp97_000\\Downloads\\ITC-basic-2.txt"};
		
		ArrayList<NetworkGraph> configurations = new ArrayList<>();
		for(String s: fileNames) {
			NetworkGraph network = new NetworkGraph(s);
			configurations.add(network);
			System.out.println("Added the following network configuration:");
			System.out.println(network.toString());
		}
	}

}
