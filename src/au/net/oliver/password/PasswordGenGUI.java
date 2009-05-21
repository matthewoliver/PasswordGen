package au.net.oliver.password;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PasswordGenGUI extends JFrame {

	private List<String> wordList1;
	private List<String> wordList2;
	private int width = PasswordGen.DEFAULT_WIDTH;
	private int height = PasswordGen.DEFAULT_HEIGHT;

	private JButton generateButton;
	private JTextField password;

	private ClipBoard clipBoard;

	public PasswordGenGUI() {
		super("Password Generator");

		initGUI();
	}

	public PasswordGenGUI(List<String> wordList1, List<String> wordList2) {
		super("Password Generator");

		setWordList1(wordList1);
		setWordList2(wordList2);

		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(width, height);

		//Create the clipboard manager
		clipBoard = new ClipBoard();

		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		password = new JTextField("");
		generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				generatePassword();
			}

		});

		mainPanel.add(generateButton);
		mainPanel.add(password);

		this.getContentPane().add(mainPanel);
	}

	private void generatePassword() {
		Random gen = new Random();

		String rndPassword = "";

		//Randomly get first word
		String word1, word2;
		if (wordList1.size() > 0) {
			word1 = wordList1.get(gen.nextInt(wordList1.size()));
		} else {
			word1 = "";
		}
		if (wordList2.size() > 0) {
			word2 = wordList2.get(gen.nextInt(wordList2.size()));
		} else {
			word2 = "";
		}
		int num = (gen.nextInt(9)) + 1;

		rndPassword = word1 + word2 + Integer.toString(num);

		while (rndPassword.length() < 8) {
			num = (gen.nextInt() % 9) + 1;
			rndPassword += Integer.toString(num);
		}

		//We have our password so add it to the text filed.
		password.setText(rndPassword);
		clipBoard.setClipboardContents(rndPassword);

	}

	public void setWordList1(List<String> wordList1) {
		this.wordList1 = wordList1;
	}

	public List<String> getWordList1() {
		return wordList1;
	}

	public void setWordList2(List<String> wordList2) {
		this.wordList2 = wordList2;
	}

	public List<String> getWordList2() {
		return wordList2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.setSize(width, height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.setSize(width, height);
	}
}
