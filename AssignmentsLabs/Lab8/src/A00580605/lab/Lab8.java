package A00580605.lab;

import java.awt.EventQueue;
import javax.swing.JFrame;
import A00580605.lab.ui.Lab8Frame;

public class Lab8 extends JFrame {

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
					Lab8Frame frame = new Lab8Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
