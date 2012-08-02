package benCalc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")

/*
 * A JButton whose font resizes dynamically. 
 */
public class JCalcButton extends JButton {

	
	/*
	 * Constructor- simply calls the superclass constructor. 
	 */
	public JCalcButton(String buttonText) {
		//Make sure we draw the regular JButton portion of things! 
		super(buttonText);
	}
	
	
	/* Override method- we need to paint the Button's string scale with the size of the button itself. 
	 * For simplicity's sake, we assume that the font height should be 50% of the height or width of the button- whichever is smallest. 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics graphics){
		
		Graphics temporaryGraphics = graphics.create();
		
		//Let's turn anti-aliasing on, because things will look much better if the button is large. 
		//Also, anti-aliasing is just cool! 
		((Graphics2D)temporaryGraphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		//First, we need to get the width/height of the Button we're enclosed in. 
		//This will help us decide the font size to use later. 
		int buttonWidth = (int) getSize().getWidth();
		int buttonHeight = (int) getSize().getHeight();

		//Calculate the font size we have to work with. If the button is super skinny or super wide, we will need to make sure the text doesn't 
		//get too large and get clipped by the button's area. 
		int pixelsToWorkWith = (int) Math.min(buttonWidth, buttonHeight);

		//Set the font size to something relative, according to the number of pixels we have to work with 
		//for the button.
		this.setFont(this.getFont().deriveFont(.35f * ((float)pixelsToWorkWith)));

		//Paint the graphics! 
		super.paintComponent(temporaryGraphics);
		
		temporaryGraphics.dispose();
	}

}