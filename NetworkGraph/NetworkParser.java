package NetworkGraph;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkParser {
	private static final String DELIM = " ";
	private static boolean verbose = true; 
	public static ArrayList<String[]> parse(String fileName) {
		if(verbose) {
			System.out.println("Beginning to parse " +  fileName);
		}
		ArrayList<String[]> fileContents = new ArrayList<>();
		try {
			File f = new File(fileName);

			Scanner input = new Scanner(f);

			while (input.hasNext()) {
				String line = input.nextLine();
				line = processLine(line);

				String[] contents = line.split(DELIM);

				if (verified(contents)) {
					fileContents.add(contents);
				} else {
					System.out.println("Line malformed during parsing " +  f.getName() +" : " + stringArray(contents));
				}
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File Not Found");
		}
		return fileContents;
	}

	private static String processLine(String line) {
		String newLine = line;
		newLine = newLine.replace("   ", " 0 ");
		return newLine;
	}

	private static boolean verified(String[] line) {
		if (line.length != 6 || (line.length != 7)) {
			if(line.length == 7){
				if(!line[6].equals("IP")){
					return false;
				}
			}
		}
		if(line.length == 6){
			try {
				Integer.parseInt(line[0]);
				Integer.parseInt(line[2]);
				Integer.parseInt(line[3]);
				Integer.parseInt(line[4]);
				Integer.parseInt(line[5]);
			} catch (Exception e) {
				return false;
			}
		}
		else if(line.length == 7) {
			try {
				//Default checks
				Integer.parseInt(line[0]);
				Integer.parseInt(line[2]);
				Integer.parseInt(line[3]);
				Integer.parseInt(line[4]);
				Integer.parseInt(line[5]);
				//Check IPv4 for host
				String ipLine = line[1];
				if(ipLine.length() > 15 || ipLine.length()<7){
					throw new Exception("Invalid Ip length");
				}
				String[] ip = ipLine.split(".");
				Integer segment1 = Integer.parseInt(ip[0]);
				Integer segment2 = Integer.parseInt(ip[1]);
				Integer segment3 = Integer.parseInt(ip[2]);
				Integer segment4 = Integer.parseInt(ip[3]);
				
				validateIpSegment(segment1);
				validateIpSegment(segment2);
				validateIpSegment(segment3);
				validateIpSegment(segment4);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

	private static String stringArray(String[] str) {
		String output = "";

		for (String s : str) {
			output += s + " ";
		}

		return output;

	}
	private static void validateIpSegment(Integer ipSeg) throws Exception {
		if(ipSeg < 0 || ipSeg > 255) {
			throw new Exception(ipSeg + " is an invalid portion of an IPv4 address");
		}
	}
	public static byte[] convertToIP(String ipLine) {
		System.out.println(ipLine);
		String[] ip = ipLine.split(".");
		System.out.println(ip.length);
		
		byte[] address = new byte[4];
		try{
		Integer segment1 = Integer.parseInt(ip[0]);
		Integer segment2 = Integer.parseInt(ip[1]);
		Integer segment3 = Integer.parseInt(ip[2]);
		Integer segment4 = Integer.parseInt(ip[3]);

		address[0] = segment1.byteValue();
		address[1] = segment2.byteValue();
		address[2] = segment3.byteValue();
		address[4] = segment4.byteValue();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("issue parsing ip");
		}
		return address;
	}
}
