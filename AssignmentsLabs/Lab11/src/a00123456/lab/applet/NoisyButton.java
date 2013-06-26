package a00123456.lab.applet;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class NoisyButton extends JApplet 
							implements ActionListener {

	
	private static final long serialVersionUID = 1L;

	JButton buttonPlay, buttonLoop, buttonStop;

	AudioClip audioClip;

	@SuppressWarnings("deprecation")
	public void init() {

		// set up the media files
		URL urlImage = getClass().getResource("resources/Dog.gif");
		URL urlAudio = getClass().getResource("resources/bark.aiff");
		audioClip = JApplet.newAudioClip(urlAudio);

		Container c = getContentPane();

		// set up the buttons
		buttonPlay = new JButton("Woof!");
		buttonPlay.addActionListener(this);
		buttonLoop = new JButton("Woof...");
		buttonLoop.addActionListener(this);
		buttonStop = new JButton("QUIET!");
		buttonStop.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(Color.red);
		panel.add(buttonPlay);
		panel.add(buttonLoop);

		JLabel dog = new JLabel(new ImageIcon(urlImage));
		panel.add(dog);
		panel.add(buttonStop);
		c.add(panel);
	}
	
	public void stop(){
		
		audioClip.stop();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonPlay))
			audioClip.play();
		else if (e.getSource().equals(buttonLoop))
			audioClip.loop();
		else if (e.getSource().equals(buttonStop))
			audioClip.stop();
	}
}
