package A00580605.lab;

import java.awt.EventQueue;
import javax.swing.JFrame;
import A00580605.lab.ui.Lab7Frame;

public class Lab7 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab7Frame frame = new Lab7Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
