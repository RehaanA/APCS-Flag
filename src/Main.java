import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	/**
	 * This is the main method, and it simply adds the instance of FlagFrame to an instance of JFrame, and displays the frame.
	 * This has to be done, as my FlagFrame class extends JPanel, and not JFrame itself.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		//EXIT_ON_CLOSE is accessed statically
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("US Flag");
		
		FlagFrame flagFrame = new FlagFrame();
		frame.setSize(700, 385);
		frame.setBackground(Color.WHITE);
		//Displays JFrame
		frame.add(flagFrame);	
	}
}