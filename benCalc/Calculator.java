package benCalc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
//Not strictly necessary to import things separately...
//but it helps to call out JUST the things you need in a particular file. 
import javax.swing.*;


/*
 * A general-purpose calculator container. 
 * Contains a menu bar for switching look-and-feels, and an Interface 
 * that creates a simple calculator UI. 
 */
public class Calculator {

	protected CalculatorFrontEnd calcFrontEnd; //This interface creates the calculator's UI. 
	
    //These enumerations will be used for the look and feel settings, they will 
	//act as keys in our look and feel hashMap. 
    enum CalcLookAndFeel{
    	MACOSX,
    	UNIX,
    	WINDOWS,
    	NIMBUS
    }
    
	//Create a map that hashes on an OS type (in this case, the enum we just created above)
    // and stores a package String representing the look and feel we want to dynamically load. 
	HashMap<CalcLookAndFeel, String> lookAndFeelTable = new HashMap<CalcLookAndFeel, String>(); 

	//Our strings to represent the look and feels
    static String MACOSX = new String ("Mac OSX");
    static String UNIX = new String ("Unix");
    static String WINDOWS = new String ("Windows");
    static String NIMBUS = new String ("Nimbus");

    //A place to store the JFrame that we create. This is necessary because it's the one thing our 
    //SimpleCalculatorFrontEnd requires to do its job. 
    private JFrame calcFrame; 
    
    /*
     * Sets the look and feel, sets up the menu bar, and its associated action listener, 
     * Creates a CalculatorFrontEnd to delegate the creation of UI widgets. 
     */
    public Calculator(){
    	//Set up our look and feel HashMap. 
        lookAndFeelTable.put(CalcLookAndFeel.MACOSX, new String("com.apple.laf.AquaLookAndFeel"));
        lookAndFeelTable.put(CalcLookAndFeel.UNIX, new String("com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
        lookAndFeelTable.put(CalcLookAndFeel.WINDOWS, new String("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
        lookAndFeelTable.put(CalcLookAndFeel.NIMBUS, new String("javax.swing.plaf.nimbus.NimbusLookAndFeel"));

        //Try to set the look and feel to Nimbus, which rocks. 
        try {
        	UIManager.setLookAndFeel(lookAndFeelTable.get(CalcLookAndFeel.NIMBUS));
        }
        //In reality, we'd catch several exceptions here, and act on each different exception type. However, 
        //for brevity, just catch any exception just in case we can't use Nimbus (ex: the user has an old JRE). 

        //Ignoring the error in this case isn't terrible, since the user doesn't even know we tried to do it. 
        catch (Exception e) {
        }
        
        //Create the calculator front end instance.
        //Note the usage of the CalculatorFrontEnd- we could easily code up a Scientific calculator in addition to this 
        //simple one, and have either one get instantiated here. That would require a more modular approach than what 
        //the SimpleCalculatorFrontEnd currently implements, though. 
        
         CalculatorFrontEnd simpleCalcUI= new SimpleCalculatorFrontEnd();
         
    	//Checking the CalculatorFrontEnd Interface code documentation, we can see that we'll need a JFrame's Container 
        //before we can create our Simple Calculator Interface. 
        calcFrame = new JFrame("BenCalc Demo");

        calcFrame.setSize(new Dimension(400,400));
        calcFrame.setPreferredSize(new Dimension(400,400));
        calcFrame.setMaximumSize(new Dimension(500,500)); //Doesn't seem to work. Oh, well. 
        calcFrame.setMinimumSize(new Dimension(400,400));

        //Set up the menu. Any calculator worth its salt has gotta have a menu! 
        setupCalcMenu(calcFrame); 
        //Set up the content pane.
        simpleCalcUI.createCalculatorLayout(calcFrame.getContentPane());

        //Display the window.
        calcFrame.pack();
        calcFrame.setVisible(true);
        
        //Exit the application if we close the window. 
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /*
     * This method sets the calculator look and feel to one of the types in the CalcLookAndFeel enumeration. 
     * If the attempt to set the Look and Feel fails, we exit after quietly doing nothing with the exception. 
     * In production code, of course, there'd be error checks, dialog messages to the user about what failed & why, etc. here. 
     * @param lnfType One of the look-and-feel types in the CalcLookAndFeel enum. 
     */
    public void setCalculatorLookAndFeel(CalcLookAndFeel lnfType)
    {
    	String lnfPackageString; 
    	lnfPackageString = lookAndFeelTable.get(lnfType);
    	
    	//Quietly leave, just in case we got nothing back from the look and feel table. 
    	if (lnfPackageString == null)
    		return; 
    	
    	try {
    		//The frame size gets reset to the default after the look and feel is changed. 
    		//So, save the original size, and restore it once the change has been made. 
    		Dimension originalFrameSize = calcFrame.getSize();
    		UIManager.setLookAndFeel(lnfPackageString);
    		SwingUtilities.updateComponentTreeUI(calcFrame);
    		calcFrame.pack(); 
    		calcFrame.setSize(originalFrameSize);
    	}

        catch (UnsupportedLookAndFeelException e) {
           // handle exception
        	JOptionPane.showMessageDialog(calcFrame,"This Look and Feel is unsupported.", "Look and Feel Loading Error",  JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException e) {
           // handle exception
        	JOptionPane.showMessageDialog(calcFrame,"Unable to load class for this Look and Feel.", "Look and Feel Loading Error",  JOptionPane.ERROR_MESSAGE);
        }
        catch (InstantiationException e) {
           // handle exception
        	JOptionPane.showMessageDialog(calcFrame,"Could not insantiate this Look and Feel.", "Look and Feel Loading Error",  JOptionPane.ERROR_MESSAGE);

        }
        catch (IllegalAccessException e) {
        	JOptionPane.showMessageDialog(calcFrame,"Access violation while loading this Look and Feel.", "Look and Feel Loading Error",  JOptionPane.ERROR_MESSAGE);
           // handle exception
        }
    }
    
    /* 
     * This sets up an options menu with items for the four look-and-feels that the 
     * calculator supports. 
     */
    private void setupCalcMenu(JFrame frame)
    {
    	//Create an 'Options' menu bar. 0
	    JMenuBar calculatorMenuBar = new JMenuBar();
	    JMenu optionsMenu = new JMenu("Options");

	    //Create a look and feel context menu, with four options in it- 
	    //Mac, Unix, Windows, and Nimbus. 
	    //Add an action listener to each menu item so we can tell when they're clicked. 
	    JMenu lookAndFeelMenu = new JMenu("Look and Feel"); 
	    JMenuItem macLnf = new JMenuItem(MACOSX);
	    lookAndFeelMenu.add(macLnf);
	    macLnf.addActionListener(new CalcMenuActionListener(this));
	    JMenuItem unixLnf = new JMenuItem("Unix");
	    lookAndFeelMenu.add(unixLnf);
	    unixLnf.addActionListener(new CalcMenuActionListener(this));
	    JMenuItem windowsClassicLnf = new JMenuItem("Windows");
	    lookAndFeelMenu.add(windowsClassicLnf);
	    windowsClassicLnf.addActionListener(new CalcMenuActionListener(this));
	    JMenuItem nimbusLnf = new JMenuItem("Nimbus");
	    lookAndFeelMenu.add(nimbusLnf);
	    nimbusLnf.addActionListener(new CalcMenuActionListener(this));
	    
	    lookAndFeelMenu.addActionListener(new CalcMenuActionListener(this));
	    
	    optionsMenu.add(lookAndFeelMenu); 
	    calculatorMenuBar.add(optionsMenu);
	    frame.setJMenuBar(calculatorMenuBar);
	}
    
//An inner-class ActionListener! 
//This action listener is for switching up the calculator look and feel. 
  
    /*
     * An Inner class action listener that attaches to the menu, 
     * and decides how to appropriately 
     */
    public class CalcMenuActionListener implements ActionListener{

    	Calculator calc; // a reference to the calculator in question!  
    	
    	public CalcMenuActionListener( Calculator calc)
    	{
    		this.calc = calc;
    	}
    	  public void actionPerformed(ActionEvent e) {
    		  //A debug message, purposefully left in the code. 
    	    System.out.println("Selected: " + e.getActionCommand());

    	    
    	    	//Depending on which menu item is clicked, we should 
    	        //set the look and feel accordingly. 
    		    if (e.getActionCommand().equals(Calculator.MACOSX))
    		    {
    		    	calc.setCalculatorLookAndFeel(CalcLookAndFeel.MACOSX);   		    	
    		    }
    		    else if (e.getActionCommand().equals(Calculator.UNIX))
    		    {
    		    	calc.setCalculatorLookAndFeel(CalcLookAndFeel.UNIX);

    		    }
    		    else if (e.getActionCommand().equals(Calculator.WINDOWS))
    		    {
    		    	calc.setCalculatorLookAndFeel(CalcLookAndFeel.WINDOWS);
    		    	
    		    }
    		    else if (e.getActionCommand().equals(Calculator.NIMBUS))
    		    {
    		    	calc.setCalculatorLookAndFeel(CalcLookAndFeel.NIMBUS);   		    	
    		    }
    	    }
    	  }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator bensSimpleCalculator = new Calculator();
	}
    
        
    
}



