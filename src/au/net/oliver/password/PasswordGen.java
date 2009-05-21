package au.net.oliver.password;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class PasswordGen {
	public static int DEFAULT_WIDTH = 400;
	public static int DEFAULT_HEIGHT = 150;

	public static void loadList(File file, List<String> list) {
		try {

			list.clear();
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}

			reader.close();

		} catch (IOException ex) {
			System.out.println("File: " + file.getAbsolutePath() + " caused an IOException!");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> wordlist1 = new Vector<String>();
		List<String> wordlist2 = new Vector<String>();

		int width = DEFAULT_WIDTH;
		int height = DEFAULT_HEIGHT;

		if (args.length == 0) {
			System.out.println("Error you must include at least 1 word list.");
		} else if (args.length == 1) {
			File wordFile1 = new File(args[0]);
			loadList(wordFile1, wordlist1);

			//Only 1 list so, use the one list for both word lists
			wordlist2 = wordlist1;
		} else {
			// two word lists sent in.
			File wordFile1 = new File(args[0]);
			loadList(wordFile1, wordlist1);

			File wordFile2 = new File(args[1]);
			loadList(wordFile2, wordlist2);
		}

		if (args.length >= 3) {
			//Size passed in in the form WxH e.g. 200x500
			String size = args[2];
			String[] sizes = size.split("x");

			if (sizes.length == 2) {
				//Split correctly its conversion time.
				try {
					width = Integer.parseInt(sizes[0]);
					height = Integer.parseInt(sizes[1]);
				} catch (NumberFormatException ex) {
					System.out.println("Failed to parse width and height, using default size!");
					width = DEFAULT_WIDTH;
					height = DEFAULT_HEIGHT;
				}
			} else {
				System.out.println("Failed to parse width and height, using default size!");
			}
		}

		//Awesome, we have the lists fully loaded, lets load the GUI
		PasswordGenGUI gui = new PasswordGenGUI(wordlist1, wordlist2);

		gui.setHeight(height);
		gui.setWidth(width);

		gui.setVisible(true);
	}
}
