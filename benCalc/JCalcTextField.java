package benCalc;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")

/*
 * A text field with pretty text, and whose font resizes dynamically. 
 */
public class JCalcTextField extends JTextField {

	
	public JCalcTextField(String fieldText) {
		//Make sure we draw the regular textField! 
		super(fieldText);
	}
	
	
	/* Override method- we need to paint the Button's string scale with the size of the field itself. 
	 * For simplicity's sake, we assume that the font height should be 50% of the height of the field.  
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	//Most of this documentation will look familiar if you already saw JCalcButton... 
	public void paintComponent(Graphics graphics){
		
		//Better not clobber the graphics! That way madness lies. 
		Graphics temporaryGraphics = graphics.create();
		
		//Again, anti-aliasing is better than nothing! 
		((Graphics2D)temporaryGraphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		//First, we need to get the height of the TextField we're enclosed in. 
		//This will help us decide the font size to use later. 
		int pixelsToWorkWith = (int) getSize().getHeight();

		//Set the font size to something relative, according to the number of pixels we have to work with 
		//for the button.
		this.setFont(this.getFont().deriveFont(.35f * ((float)pixelsToWorkWith)));

		//Paint the text field! 
		super.paintComponent(temporaryGraphics);
		
		temporaryGraphics.dispose();
	}

}