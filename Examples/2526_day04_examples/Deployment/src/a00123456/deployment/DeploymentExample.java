/**
 * Project: Deployment
 * File: DeploymentExample.java
 * Date: Oct 2, 2006
 * Time: 2:15:53 PM
 */

package a00123456.deployment;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * @author Fred Fish A00123456
 *
 */
public class DeploymentExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = JOptionPane.showInputDialog("Pick a number from 1 to 10");
		int number = new Random().nextInt(9) + 1;
		int guess = Integer.parseInt(value);
		String message;
		if (guess == number) {
			message = String.format("Correct! The number is %d", number);
		} else {
			message = String.format("Sorry. The number was %d", number);
		}
		JOptionPane.showMessageDialog(null, message);
	}

}
