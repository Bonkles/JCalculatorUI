package benCalc;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTextField;

/*
 * A simple calculator user interface (not *Interface*), like the ones that you'd get in grade school for a dollar. 
 */
public class SimpleCalculatorFrontEnd implements CalculatorFrontEnd{
		
	//This function adds all the buttons and the calculator display to the Container, 
	//creating a simple calculator layout. 
    public void createCalculatorLayout(Container calcContainer) {
    		
    	//First, our calculator will need a place to display results. 
    	//JCalcTextField is basically a regular text field, but with magical 
    	//resizing powers! 
		JCalcTextField displayField = new JCalcTextField("0");
		//make the value appear on the right-hand side of the display. 
    	displayField.setHorizontalAlignment(JTextField.RIGHT); 

    	//The buttons from zero to nine... 
		JCalcButton zeroButton = new JCalcButton(new String("0"));
		JCalcButton oneButton = new JCalcButton(new String("1"));
		JCalcButton twoButton = new JCalcButton(new String("2"));
		JCalcButton threeButton = new JCalcButton(new String("3"));
		JCalcButton fourButton = new JCalcButton(new String("4"));
		JCalcButton fiveButton = new JCalcButton(new String("5"));
		JCalcButton sixButton = new JCalcButton(new String("6"));
		JCalcButton sevenButton = new JCalcButton(new String("7"));
		JCalcButton eightButton = new JCalcButton(new String("8"));
		JCalcButton nineButton = new JCalcButton(new String("9"));
	
		//The 'other' buttons on the calculator interface. 
		//Nobody ever knows WHAT these 'M' buttons do. So, add some tooltips for them! 
		JCalcButton mcButton = new JCalcButton(new String("MC"));
		mcButton.setToolTipText("Clears the number stored in memory.");
		JCalcButton mrButton = new JCalcButton(new String("MR"));
		mrButton.setToolTipText("Recalls the number stored in memory.");
		JCalcButton msButton = new JCalcButton(new String("MS"));
		msButton.setToolTipText("Stores the displayed number in memory.");		
		JCalcButton mPlusButton = new JCalcButton(new String("M+"));
		mPlusButton.setToolTipText("Adds the displayed number to the number in memory.");		
		JCalcButton mMinusButton = new JCalcButton(new String("M-"));
		mMinusButton.setToolTipText("Subtracts the displayed number from the number in memory.");		
		JCalcButton bkspButton = new JCalcButton(new String("←"));//Hooray for unicode strings! 
		JCalcButton ceButton = new JCalcButton(new String("CE"));
		JCalcButton cButton = new JCalcButton(new String("C"));
		JCalcButton PlusMinusButton = new JCalcButton(new String("±"));
		JCalcButton sqrtButton = new JCalcButton(new String("√"));
	
		JCalcButton divButton = new JCalcButton(new String("/"));
		JCalcButton multButton = new JCalcButton(new String("*"));
		JCalcButton minusButton = new JCalcButton(new String("-"));
		JCalcButton plusButton = new JCalcButton(new String("+"));
		JCalcButton decimalButton = new JCalcButton(new String("."));
		JCalcButton percentageButton = new JCalcButton(new String("%"));
		JCalcButton inverseButton = new JCalcButton(new String("1/x"));
		JCalcButton equalsButton = new JCalcButton(new String("="));
		
		//Okay, we've created our buttons but still haven't done anything useful with them yet. 
		
		//To effectively lay them out, we'll use a layout manager called GridBag.
		//Why Gridbag? because our calculator components (buttons, a display field) lend themselves well to a grid layout. 
		//GridBag is more useful than a plain GridLayout in this case, because some fields & buttons (the zero button, the equals button) 
		//get more screen space (they take up two cells). 
		
		//Define the layout manager, component orientation, and gridbag constraints so that 
		//we can start adding things. 
		calcContainer.setLayout(new GridBagLayout());
		
		//We'll add components from top-to-bottom, left to right. 
		calcContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		
		GridBagConstraints gbc = new GridBagConstraints(); 

	    
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.weightx = 0.5;// Make sure the x and y weights are identical-  
	    gbc.weighty = 0.5;//we don't want them to grow/shrink at different rates while the calculator resizes.
	    gbc.anchor = GridBagConstraints.CENTER; //Anchor each component to the center of its 'cell'. 
	    
	    //The fun part! Start assembling the calculator UI layout. 
	    
	    //Start with the display itself. 
	    gbc.gridy = 0; 
	    gbc.gridx = 0; 
	    gbc.gridwidth = 5;
	    gbc.gridheight = 1; 
	    calcContainer.add(displayField, gbc);

	    //We've added the display, so make sure our buttons have a bit of space between each other. 
	    gbc.insets = new Insets(5,5,5,5); 

	    //Add the first real row of buttons: 
	    //MC MR MS M+ M-
	    
	    //Each button (with two exceptions) will have a grid width and height of 1- 
	    //so they will all be the same size. 
	    gbc.gridwidth = 1;  
	    gbc.gridheight = 1; 
	    
	    //Start two rows down, since the displayField took up the first two rows. 
	    gbc.gridy = 2; 	
	    //start at the leftmost column, add the first button... 
	    gbc.gridx = 0;  
	    calcContainer.add(mcButton, gbc); 

	    //move to the next column, and so on. 
	    gbc.gridx = 1;  
	    calcContainer.add(mrButton, gbc); 

	    gbc.gridx = 2;  
	    calcContainer.add(msButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(mMinusButton, gbc); 
    	
	    gbc.gridx = 4;  
	    calcContainer.add(mPlusButton, gbc); 

	    //Add the second real row of buttons: 
	    //← CE C ± √
	    gbc.gridy = 3; 	
	    gbc.gridx = 0;
	    calcContainer.add(bkspButton, gbc); 

	    gbc.gridx = 1;  
	    calcContainer.add(ceButton, gbc); 

	    gbc.gridx = 2;  
	    calcContainer.add(cButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(PlusMinusButton, gbc); 
    	
	    gbc.gridx = 4;  
	    calcContainer.add(sqrtButton, gbc); 

	    //Add the third real row of buttons: 
	    //7 8 9 / %
	    gbc.gridy = 4; 	
	    gbc.gridx = 0;
	    calcContainer.add(sevenButton, gbc); 

	    gbc.gridx = 1;  
	    calcContainer.add(eightButton, gbc); 

	    gbc.gridx = 2;  
	    calcContainer.add(nineButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(divButton, gbc); 
    	
	    gbc.gridx = 4;  
	    calcContainer.add(percentageButton, gbc); 

	    //Add the fourth real row of buttons: 
	    //7 8 9 / %
	    gbc.gridy = 5; 	
	    gbc.gridx = 0;
	    calcContainer.add(fourButton, gbc); 

	    gbc.gridx = 1;  
	    calcContainer.add(fiveButton, gbc); 

	    gbc.gridx = 2;  
	    calcContainer.add(sixButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(multButton, gbc); 
    	
	    gbc.gridx = 4;  
	    calcContainer.add(inverseButton, gbc); 

	    //Add the fifth real row of buttons: 
	    //7 8 9 / %
	    gbc.gridy = 6; 	
	    gbc.gridx = 0;
	    calcContainer.add(oneButton, gbc); 

	    gbc.gridx = 1;  
	    calcContainer.add(twoButton, gbc); 

	    gbc.gridx = 2;  
	    calcContainer.add(threeButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(minusButton, gbc); 

	    //Skip adding something to column 4 for now: the equals sign will take up extra room. 

	    //Add the sixth real row of buttons: 
	    //7 8 9 / %
	    gbc.gridy = 7; 	
	    gbc.gridx = 0;
	    gbc.gridwidth = 2; //That's right- the zero button will take up two columns.  
	    calcContainer.add(zeroButton, gbc); 

	    gbc.gridwidth = 1; //Set the width back to 1, so the rest of the buttons aren't too big. 
	    gbc.gridx = 2;  
	    calcContainer.add(decimalButton, gbc); 

	    gbc.gridx = 3;  
	    calcContainer.add(plusButton, gbc); 
    	
	    //Backtrack to the previous row- row 5. 
	    gbc.gridy = 6; 
	    gbc.gridx = 4;
	    gbc.gridheight = 2; //The equals button is two grid units high!
	    calcContainer.add(equalsButton, gbc); 

    }

}
