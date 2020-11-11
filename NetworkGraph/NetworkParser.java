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
			System.out.println("file Not Found");
		}
		return fileContents;
	}

	private static String processLine(String line) {
		String newLine = line;
		newLine = newLine.replace("   ", " 0 ");
		return newLine;
	}

	private static boolean verified(String[] line) {
		if (line.length != 6) {
			return false;
		}

		try {
			Integer.parseInt(line[0]);
			Integer.parseInt(line[2]);
			Integer.parseInt(line[3]);
			Integer.parseInt(line[4]);
			Integer.parseInt(line[5]);
		} catch (Exception e) {
			return false;
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
}
