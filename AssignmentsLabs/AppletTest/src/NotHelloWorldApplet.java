import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class NotHelloWorldApplet extends JApplet {

	public void init() {
		String input = getParameter("message");
		JLabel label = new JLabel(input, SwingConstants.CENTER);
		add(label);
	}
}
